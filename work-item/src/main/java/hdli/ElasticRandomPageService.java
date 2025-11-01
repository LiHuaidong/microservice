package hdli;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ElasticRandomPageService {

    private static final String ELASTICSEARCH_URL = "https://localhost:9200";
    private static final String USERNAME = "elastic";
    private static final String PASSWORD = "=dipg+nTzeWybM1iYXHM";

    private static final Set<Integer> ALLOWED_PAGE_SIZES = new HashSet<>(Arrays.asList(10, 20, 50, 100, 1000, 10000, 100000));
    private static final ObjectMapper MAPPER = new ObjectMapper();
    // 本地回退缓存：每个 pageSize 一份游标缓存：pageNumber -> search_after 数组（上一页最后一条的 sort）
    private final Map<Integer, Map<Integer, List<Object>>> fallbackPageCursor = new ConcurrentHashMap<>();
    // Redisson 客户端（可选）
    private volatile org.redisson.api.RedissonClient redissonClient;
    private volatile String redisNamespace = "search_after:pager"; // 可配置

    private static String buildSortArrayJson(List<String> sortFields) {
        if (sortFields == null || sortFields.isEmpty()) {
            throw new IllegalArgumentException("sortFields 不能为空，search_after 需要稳定排序");
        }
        StringBuilder sb = new StringBuilder().append('[');
        for (int i = 0; i < sortFields.size(); i++) {
            String raw = sortFields.get(i);
            String field = raw;
            String order = "asc";
            int colon = raw.lastIndexOf(':');
            if (colon > 0 && colon < raw.length() - 1) {
                field = raw.substring(0, colon);
                order = raw.substring(colon + 1).trim().toLowerCase();
                if (!order.equals("asc") && !order.equals("desc")) {
                    order = "asc";
                }
            }
            sb.append('{')
                    .append('"').append(escapeJson(field)).append('"')
                    .append(':')
                    .append('{')
                    .append("\"order\":\"").append(order).append("\"");
            sb.append('}').append('}');
            if (i < sortFields.size() - 1) sb.append(',');
        }
        sb.append(']');
        return sb.toString();
    }

    private static String toJsonValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof Number || value instanceof Boolean) {
            return String.valueOf(value);
        }
        if (value instanceof java.time.temporal.TemporalAccessor) {
            String s = value.toString();
            return '"' + escapeJson(s) + '"';
        }
        if (value instanceof java.util.Date) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            String s = sdf.format((java.util.Date) value);
            return '"' + escapeJson(s) + '"';
        }
        String s = String.valueOf(value);
        return '"' + escapeJson(s) + '"';
    }

    // -------------------- 下面是复用/拷贝的工具与请求方法 --------------------

    private static String escapeJson(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < 0x20) {
                        String hex = Integer.toHexString(c);
                        sb.append("\\u");
                        for (int pad = hex.length(); pad < 4; pad++) sb.append('0');
                        sb.append(hex);
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }

    private static String normalizeJsonObject(String candidate, String defaultJsonObject) {
        if (candidate == null || candidate.trim().isEmpty()) {
            return defaultJsonObject;
        }
        String trimmed = candidate.trim();
        if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
            return trimmed;
        }
        String middle = trimmed;
        if (middle.startsWith("{")) {
            middle = middle.substring(1);
        }
        if (middle.endsWith("}")) {
            middle = middle.substring(0, middle.length() - 1);
        }
        if (middle.startsWith(",")) {
            middle = middle.substring(1);
        }
        if (middle.endsWith(",")) {
            middle = middle.substring(0, middle.length() - 1);
        }
        return "{" + middle + "}";
    }

    // HTTP 与认证
    private static HttpClient createTrustAllHttpClient() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[]{
                new javax.net.ssl.X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        javax.net.ssl.SSLContext sslContext = javax.net.ssl.SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        return HttpClient.newBuilder().sslContext(sslContext).build();
    }

    private static String getBasicAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + java.util.Base64.getEncoder().encodeToString(credentials.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    // 核心：使用 search_after 的请求
    private static String queryDocSearchAfter(String index,
                                              String baseQueryJson,
                                              List<String> sortFields,
                                              List<Object> searchAfterValues,
                                              int size,
                                              String username,
                                              String password) throws Exception {
        String url = ELASTICSEARCH_URL + "/" + index + "/_search";

        HttpClient httpClient = createTrustAllHttpClient();
        String authHeader = getBasicAuthHeader(username, password);

        String sortArrayJson = buildSortArrayJson(sortFields);

        StringBuilder finalBodyBuilder = new StringBuilder();
        finalBodyBuilder.append(baseQueryJson, 0, baseQueryJson.length() - 1);
        finalBodyBuilder.append(",\"size\":").append(size);
        finalBodyBuilder.append(",\"sort\":").append(sortArrayJson);
        if (searchAfterValues != null && !searchAfterValues.isEmpty()) {
            finalBodyBuilder.append(",\"search_after\":[");
            for (int i = 0; i < searchAfterValues.size(); i++) {
                finalBodyBuilder.append(toJsonValue(searchAfterValues.get(i)));
                if (i < searchAfterValues.size() - 1) finalBodyBuilder.append(',');
            }
            finalBodyBuilder.append(']');
        }
        finalBodyBuilder.append('}');
        String requestBody = finalBodyBuilder.toString();

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", authHeader)
                .timeout(java.time.Duration.ofSeconds(15))
                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // 解析 hits（使用 Jackson）
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> extractHits(String json) throws Exception {
        Map<String, Object> obj = MAPPER.readValue(json, Map.class);
        Object hitsObj = obj.get("hits");
        if (!(hitsObj instanceof Map)) {
            return Collections.emptyList();
        }
        Object arr = ((Map<String, Object>) hitsObj).get("hits");
        if (!(arr instanceof List)) {
            return Collections.emptyList();
        }
        return (List<Map<String, Object>>) arr;
    }

    @SuppressWarnings("unchecked")
    private static List<Object> extractLastSort(String json) throws Exception {
        Map<String, Object> obj = MAPPER.readValue(json, Map.class);
        Object hitsObj = obj.get("hits");
        if (!(hitsObj instanceof Map)) {
            return Collections.emptyList();
        }
        Object arr = ((Map<String, Object>) hitsObj).get("hits");
        if (!(arr instanceof List)) {
            return Collections.emptyList();
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) arr;
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        Object sort = list.get(list.size() - 1).get("sort");
        if (sort instanceof List) {
            return (List<Object>) sort;
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) throws Exception {
        ElasticRandomPageService svc = new ElasticRandomPageService();
        svc.configureRedis("redis://127.0.0.1:6379", null, null);
        String index = "work-items";
        String queryJson = null; // 默认 match_all + track_total_hits

        int[] sizes = new int[]{10, 20, 50, 100, 1000, 10000, 100000};
        int targetPages = 20; // 测 20 页
        java.util.Random rnd = new java.util.Random();

        for (int size : sizes) {
            System.out.println("\n=== Random access paging test, size=" + size + " ===");
            long r0 = System.nanoTime();
            for (int i = 0; i < targetPages; i++) {
                int page = 1 + rnd.nextInt(targetPages); // 1..targetPages
                long sPage = System.nanoTime();
                String resp = svc.fetchPage(index, size, page, queryJson);
                long ePage = System.nanoTime();
                try {
                    List<Map<String, Object>> hits = extractHits(resp);
                    System.out.println("[rand] page=" + page + ", hits=" + (hits != null ? hits.size() : 0) + ", costMs=" + ((ePage - sPage) / 1_000_000.0));
                } catch (Exception ignore) {
                    System.out.println("[rand] page=" + page + ", parseError, costMs=" + ((ePage - sPage) / 1_000_000.0));
                }
            }
            long r1 = System.nanoTime();
            System.out.println("[rand] totalCostMs=" + ((r1 - r0) / 1_000_000.0));

            System.out.println("\n=== Sequential paging test, size=" + size + " ===");
            long t0 = System.nanoTime();
            for (int p = 1; p <= targetPages; p++) {
                long sPage = System.nanoTime();
                String resp = svc.fetchPage(index, size, p, queryJson);
                long ePage = System.nanoTime();
                // 简单统计命中条数
                try {
                    List<Map<String, Object>> hits = extractHits(resp);
                    System.out.println("[seq] page=" + p + ", hits=" + (hits != null ? hits.size() : 0) + ", costMs=" + ((ePage - sPage) / 1_000_000.0));
                } catch (Exception ignore) {
                    System.out.println("[seq] page=" + p + ", parseError, costMs=" + ((ePage - sPage) / 1_000_000.0));
                }
            }
            long t1 = System.nanoTime();
            System.out.println("[seq] totalCostMs=" + ((t1 - t0) / 1_000_000.0));
        }

        System.out.println("\nPerformance tests completed.");
    }

    // 对外主方法：随机访问第 n 页
    public String fetchPage(String index, Integer pageSize, Integer pageNumber, String queryJson) throws Exception {
        return fetchPage("defaultUser", "defaultQuery", index, pageSize, pageNumber, queryJson);
    }

    // 多用户 + 查询ID 维度隔离的接口
    public String fetchPage(String userKey,
                            String queryId,
                            String index,
                            Integer pageSize,
                            Integer pageNumber,
                            String queryJson) throws Exception {
        if (pageSize == null || !ALLOWED_PAGE_SIZES.contains(pageSize)) {
            throw new IllegalArgumentException("pageSize 仅支持: " + ALLOWED_PAGE_SIZES);
        }
        if (pageNumber == null || pageNumber < 1) {
            throw new IllegalArgumentException("pageNumber 必须 >= 1");
        }

        String baseQueryJson = normalizeJsonObject(
                queryJson,
                "{\"query\":{\"match_all\":{}},\"track_total_hits\":true}"
        );

        // 稳定排序：id 升序
        List<String> sortFields = Arrays.asList("id:asc");

        // 第 1 页直接查，无需游标
        List<Object> searchAfter = null;
        if (pageNumber > 1) {
            searchAfter = ensureCursorForPage(userKey, queryId, index, baseQueryJson, sortFields, pageSize, pageNumber, USERNAME, PASSWORD);
        }

        return queryDocSearchAfter(index, baseQueryJson, sortFields, searchAfter, pageSize, USERNAME, PASSWORD);
    }

    // 预构建并返回目标页的游标（即目标页的上一页的最后一条 sort）
    private List<Object> ensureCursorForPage(String userKey,
                                             String queryId,
                                             String index,
                                             String baseQueryJson,
                                             List<String> sortFields,
                                             int pageSize,
                                             int pageNumber,
                                             String username,
                                             String password) throws Exception {
        Map<Integer, List<Object>> pageToCursor = getLocalMap(userKey, queryId, pageSize);

        // 如果已有缓存，尝试直接拿
        List<Object> cached = getCursorFromCache(userKey, queryId, pageSize, pageNumber);
        if (cached != null) {
            return cached;
        }

        // 找到已缓存的最大页，从它继续推进（最少从第 1 页开始）
        int startFrom = 1;
        for (Integer p : pageToCursor.keySet()) {
            if (p != null && p > startFrom) {
                startFrom = p;
            }
        }

        List<Object> after = getCursorFromCache(userKey, queryId, pageSize, startFrom);
        if (startFrom == 1) {
            after = null; // 第 1 页没有游标
        }

        // 从 startFrom 往后推进到目标页
        for (int p = Math.max(1, startFrom); p < pageNumber; p++) {
            String resp = queryDocSearchAfter(index, baseQueryJson, sortFields, after, pageSize, username, password);
            List<Map<String, Object>> hits = extractHits(resp);
            if (hits.isEmpty()) {
                // 数据不足，缓存空并返回空
                putCursorToCache(userKey, queryId, pageSize, p + 1, Collections.emptyList());
                return Collections.emptyList();
            }
            List<Object> nextAfter = extractLastSort(resp);
            if (nextAfter == null || nextAfter.isEmpty()) {
                putCursorToCache(userKey, queryId, pageSize, p + 1, Collections.emptyList());
                return Collections.emptyList();
            }
            // p+1 页所需的游标就是本页最后一条的 sort
            putCursorToCache(userKey, queryId, pageSize, p + 1, nextAfter);
            after = nextAfter;

            // 若不足一整页，提前终止
            if (hits.size() < pageSize) {
                break;
            }
        }
        return getCursorFromCache(userKey, queryId, pageSize, pageNumber);
    }

    // -------------------- Redis 缓存封装 --------------------
    public void configureRedis(String address, String password, String namespace) {
        org.redisson.config.Config config = new org.redisson.config.Config();
        config.useSingleServer().setAddress(address).setPassword(password);
        this.redissonClient = org.redisson.Redisson.create(config);
        if (namespace != null && !namespace.isEmpty()) {
            this.redisNamespace = namespace;
        }
    }

    private Map<Integer, List<Object>> getLocalMap(String userKey, String queryId, int pageSize) {
        // 本地使用组合 key pageSize，不细分 user/queryId，作为兜底
        return fallbackPageCursor.computeIfAbsent(pageSize, k -> new ConcurrentHashMap<>());
    }

    private String redisKey(String userKey, String queryId, int pageSize) {
        return redisNamespace + ":u:" + userKey + ":q:" + queryId + ":ps:" + pageSize;
    }

    private List<Object> getCursorFromCache(String userKey, String queryId, int pageSize, int pageNumber) {
        // 优先 Redis
        if (redissonClient != null) {
            org.redisson.api.RMap<Integer, String> map = redissonClient.getMap(redisKey(userKey, queryId, pageSize));
            String json = map.get(pageNumber);
            if (json != null) {
                try {
                    return MAPPER.readValue(json, new TypeReference<List<Object>>() {
                    });
                } catch (Exception ignored) {
                }
            }
        }
        // 回退到本地
        return getLocalMap(userKey, queryId, pageSize).get(pageNumber);
    }

    private void putCursorToCache(String userKey, String queryId, int pageSize, int pageNumber, List<Object> cursor) {
        // 写 Redis
        if (redissonClient != null) {
            org.redisson.api.RMap<Integer, String> map = redissonClient.getMap(redisKey(userKey, queryId, pageSize));
            String json = toJsonArray(cursor);
            map.put(pageNumber, json);
        }
        // 写本地回退
        getLocalMap(userKey, queryId, pageSize).put(pageNumber, cursor);
    }

    private String toJsonArray(List<Object> values) {
        try {
            return MAPPER.writeValueAsString(values);
        } catch (Exception e) {
            // 兜底：手工拼接
            if (values == null) {
                return "null";
            }
            StringBuilder sb = new StringBuilder().append('[');
            for (int i = 0; i < values.size(); i++) {
                sb.append(toJsonValue(values.get(i)));
                if (i < values.size() - 1) {
                    sb.append(',');
                }
            }
            return sb.append(']').toString();
        }
    }

}


