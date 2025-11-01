package hdli;

import java.net.http.HttpClient;
import java.util.List;

public class ElasticsearchIndexDocManager {

    private static final String ELASTICSEARCH_URL = "https://localhost:9200";

    private static final String USERNAME = "elastic";
    private static final String PASSWORD = "=dipg+nTzeWybM1iYXHM";


    public static void main(String[] args) {
        String index = "work-items";
        String docId = "1";
        String jsonPartialDoc = "{\"name\":\"test\"}";
        String username = USERNAME;
        String password = PASSWORD;
        String response = null;
        // try {
        //     response = partialUpdateDoc(index, docId, jsonPartialDoc, username, password);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // System.out.println(response);

//        String queryJson = null; // 使用方法内默认 {"query":{"match_all":{}},"track_total_hits":true}
//        int size = 20;
//        try {
//            benchmarkSearchAfterById(index, queryJson, size, username, password);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        // String docJson = "{\"name\":\"test\"}";
        // try {
        //     response = createDoc(index, docId, docJson, username, password);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // System.out.println(response);

        // try {
        //     response = getDoc(index, docId, username, password);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // System.out.println(response);


        // docJson = "{\"name\":\"test\"}";
        // try {
        //     response = updateDoc(index, docId, docJson, username, password);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // System.out.println(response);

        // try {
        //     response = deleteDoc(index, docId, username, password);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // System.out.println(response);

        buildWorkItems(index, username, password);

        System.out.println("All operations completed successfully");
    }

    private static void buildWorkItems(String index, String username, String password) {
        // 1. JDBC config
        String jdbcUrl = "jdbc:mysql://localhost:3306/work_db";
        String jdbcUser = "root";
        String jdbcPassword = "12345678";

        // 2. Get total record count
        int totalCount = 0;
        int pageSize = 100000;
        java.sql.Connection conn = null;
        java.sql.Statement stmt = null;
        java.sql.ResultSet rs = null;

        try {
            conn = java.sql.DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) as cnt FROM work_item");
            if (rs.next()) {
                totalCount = rs.getInt("cnt");
            }
            System.out.println("Total records: " + totalCount);
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }

            // 3. Paginated query and bulk insert
            int totalPages = (totalCount + pageSize - 1) / pageSize;
            for (int page = 0; page < totalPages; page++) {
                int offset = page * pageSize;
                System.out.println("Processing page " + (page + 1) + "/" + totalPages);

                // Query current page
                stmt = conn.createStatement();
                String selectSQL = "SELECT * FROM work_item LIMIT " + pageSize + " OFFSET " + offset;
                rs = stmt.executeQuery(selectSQL);

                // Convert to JSON list
                java.util.List<String> jsonList = new java.util.ArrayList<>();
                java.sql.ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    StringBuilder sb = new StringBuilder().append("{");
                    for (int i = 1; i <= columnCount; i++) {
                        String key = metaData.getColumnLabel(i);
                        Object value = rs.getObject(i);
                        sb.append("\"").append(key).append("\":");
                        if (value == null) {
                            sb.append("null");
                        } else if (value instanceof Number || value instanceof Boolean) {
                            sb.append(value.toString());
                        } else if (value instanceof java.sql.Timestamp || value instanceof java.util.Date) {
                            // Handle date fields - format as "yyyy-MM-dd HH:mm:ss"
                            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String dateStr = sdf.format((java.util.Date) value);
                            sb.append("\"").append(dateStr).append("\"");
                        } else {
                            String v = value.toString().replace("\\", "\\\\").replace("\"", "\\\"");
                            sb.append("\"").append(v).append("\"");
                        }
                        if (i < columnCount) sb.append(",");
                    }
                    sb.append("}");
                    jsonList.add(sb.toString());
                }
                rs.close();
                stmt.close();

                // 4. Bulk insert per 100 records using threads
                int bulkSize = 1000;
                int threadPoolSize = 4;
                java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(threadPoolSize);

                for (int j = 0; j < jsonList.size(); j += bulkSize) {
                    final java.util.List<String> batch = jsonList.subList(j, Math.min(j + bulkSize, jsonList.size()));
                    final java.util.List<String> batchCopy = new java.util.ArrayList<>(batch);

                    executor.submit(() -> {
                        try {
                            String bulkResp = bulkCreateDoc(index, batchCopy, username, password);
                            System.out.println("[Thread] Bulk response: " + bulkResp.toString());
                        } catch (Exception ex) {
                            System.err.println("[Thread] Error: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    });
                }

                executor.shutdown();
                executor.awaitTermination(10, java.util.concurrent.TimeUnit.MINUTES);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
            }
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 部分更新文档（使用 _update API）
     *
     * @param index          索引名
     * @param docId          文档 ID
     * @param jsonPartialDoc 更新内容（部分字段的 JSON 字符串）
     * @param username       用户名
     * @param password       密码
     * @return 响应内容
     * @throws Exception 出错时抛出
     */
    public static String partialUpdateDoc(String index, String docId, String jsonPartialDoc, String username, String password) throws Exception {
        if (docId == null || docId.isEmpty()) {
            throw new IllegalArgumentException("docId 不能为空");
        }
        String url = ELASTICSEARCH_URL + "/" + index + "/_update/" + docId;

        java.net.http.HttpClient httpClient = createTrustAllHttpClient();
        String authHeader = getBasicAuthHeader(username, password);

        // Elasticsearch "update" API 需要 {"doc": {...}} 格式
        String postBody = "{\"doc\":" + jsonPartialDoc + "}";

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", authHeader)
                .timeout(java.time.Duration.ofSeconds(15))
                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(postBody))
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * 查询文档（支持 filter/分页）
     *
     * @param index     索引名
     * @param queryJson 查询 JSON（可用 match/filter/term 等）
     * @param from      起始位置（分页）
     * @param size      查询数量（分页）
     * @param username  用户名
     * @param password  密码
     * @return 响应内容
     * @throws Exception 出错时抛出
     */
    public static String queryDoc(String index, String queryJson, int from, int size, String username, String password) throws Exception {
        String url = ELASTICSEARCH_URL + "/" + index + "/_search";

        java.net.http.HttpClient httpClient = createTrustAllHttpClient();
        String authHeader = getBasicAuthHeader(username, password);

        // 分页参数嵌入查询体
        String pageQueryBody;
        if (queryJson != null && !queryJson.trim().isEmpty()) {
            // 合并参数分页
            String trimmed = queryJson.trim();
            if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
                // 移除最后一个"}"，然后加分页参数，再闭合
                String noBrace = trimmed.substring(0, trimmed.length() - 1);
                pageQueryBody = noBrace +
                        ",\"from\":" + from +
                        ",\"size\":" + size +
                        "}";
            } else {
                throw new IllegalArgumentException("queryJson 不是合法的 JSON 对象结构");
            }
        } else {
            pageQueryBody = "{\"from\":" + from + ",\"size\":" + size + "}";
        }

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", authHeader)
                .timeout(java.time.Duration.ofSeconds(15))
                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(pageQueryBody))
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * 查询文档（基于 search_after 的深分页）
     * <p>
     * 使用场景：需要稳定排序的游标式翻页，避免深度 from+size 的性能问题。
     * 约束：必须提供稳定且唯一的排序（通常最后追加 id 做为兜底）。
     *
     * @param index             索引名
     * @param queryJson         完整查询 JSON（对象结构，如 {"query":{...},"track_total_hits":true}）。若为空将自动使用 match_all
     * @param sortFields        排序字段列表：元素形如 "field:asc" 或 "field:desc"；未指定方向默认 asc
     * @param searchAfterValues 上一页最后一条命中的 sort 值列表（与 sortFields 一一对应）。第一页可传 null 或空
     * @param size              本页大小
     * @param username          用户名
     * @param password          密码
     * @return 响应内容（原始 JSON 字符串）
     * @throws Exception 出错时抛出
     */
    public static String queryDocSearchAfter(
            String index,
            String queryJson,
            java.util.List<String> sortFields,
            java.util.List<Object> searchAfterValues,
            int size,
            String username,
            String password
    ) throws Exception {
        String url = ELASTICSEARCH_URL + "/" + index + "/_search";

        HttpClient httpClient = createTrustAllHttpClient();
        String authHeader = getBasicAuthHeader(username, password);

        // 1) 基础查询体：若未提供则使用 match_all
        String baseQueryBody = normalizeJsonObject(
                queryJson,
                "{\"query\":{\"match_all\":{}}}"
        );

        // 2) 组装 sort 数组 JSON
        String sortArrayJson = buildSortArrayJson(sortFields);

        // 3) 合并：在原对象末尾追加 size、sort、以及（可选）search_after
        StringBuilder finalBodyBuilder = new StringBuilder();
        finalBodyBuilder.append(baseQueryBody, 0, baseQueryBody.length() - 1); // 去掉最后一个 '}'
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

    /**
     * 将形如 ["fieldA:asc", "fieldB:desc", "id:asc"] 转为 ES 可用的 sort JSON 数组
     * 生成形如：[{"fieldA":{"order":"asc"}},{"fieldB":{"order":"desc"}},{"id":{"order":"asc"}}]
     */
    private static String buildSortArrayJson(java.util.List<String> sortFields) {
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
                if (!order.equals("asc") && !order.equals("desc")) order = "asc";
            }
            sb.append('{')
                    .append('"').append(escapeJson(field)).append('"')
                    .append(':')
                    .append('{')
                    .append("\"order\":\"").append(order).append("\"");
            // 可按需追加 unmapped_type 等参数
            sb.append('}').append('}');
            if (i < sortFields.size() - 1) sb.append(',');
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * 将任意值转为 JSON 值（字符串会做转义并带引号）
     */
    private static String toJsonValue(Object value) {
        if (value == null) return "null";
        if (value instanceof Number || value instanceof Boolean) {
            return String.valueOf(value);
        }
        if (value instanceof java.time.temporal.TemporalAccessor) {
            // 简单格式化为 ISO-8601 字符串
            String s = value.toString();
            return '"' + escapeJson(s) + '"';
        }
        if (value instanceof java.util.Date) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            String s = sdf.format((java.util.Date) value);
            return '"' + escapeJson(s) + '"';
        }
        // 其他一律按字符串处理
        String s = String.valueOf(value);
        return '"' + escapeJson(s) + '"';
    }

    /**
     * 简单的 JSON 字符串转义
     */
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

    /**
     * 规范化为 JSON 对象字符串：若为空用默认值；若不是以 { 开头且以 } 结尾，则包一层 { ... }。
     */
    private static String normalizeJsonObject(String candidate, String defaultJsonObject) {
        if (candidate == null || candidate.trim().isEmpty()) {
            return defaultJsonObject;
        }
        String trimmed = candidate.trim();
        if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
            return trimmed;
        }
        // 去除可能的多余大括号或逗号
        String middle = trimmed;
        if (middle.startsWith("{")) middle = middle.substring(1);
        if (middle.endsWith("}")) middle = middle.substring(0, middle.length() - 1);
        if (middle.startsWith(",")) middle = middle.substring(1);
        if (middle.endsWith(",")) middle = middle.substring(0, middle.length() - 1);
        return "{" + middle + "}";
    }

    /**
     * 创建文档（单条，指定ID）
     *
     * @param index    索引名
     * @param docId    文档ID
     * @param docJson  文档内容（json字符串）
     * @param username es用户名
     * @param password es密码
     * @return 返回响应内容
     * @throws Exception
     */
    public static String createDoc(String index, String docId, String docJson, String username, String password) throws Exception {
        String url = ELASTICSEARCH_URL + "/" + index + "/_doc/" + docId;
        java.net.http.HttpClient httpClient = createTrustAllHttpClient();
        String authHeader = getBasicAuthHeader(username, password);

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", authHeader)
                .timeout(java.time.Duration.ofSeconds(30))
                .PUT(java.net.http.HttpRequest.BodyPublishers.ofString(docJson.trim()))
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * 根据ID获取文档
     *
     * @param index    索引名
     * @param docId    文档ID
     * @param username es用户名
     * @param password es密码
     * @return 返回响应内容
     * @throws Exception
     */
    public static String getDoc(String index, String docId, String username, String password) throws Exception {
        String url = ELASTICSEARCH_URL + "/" + index + "/_doc/" + docId;
        java.net.http.HttpClient httpClient = createTrustAllHttpClient();
        String authHeader = getBasicAuthHeader(username, password);

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Authorization", authHeader)
                .timeout(java.time.Duration.ofSeconds(30))
                .GET()
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * 更新文档（按ID全量更新）
     *
     * @param index    索引名
     * @param docId    文档ID
     * @param docJson  文档内容（json字符串）
     * @param username es用户名
     * @param password es密码
     * @return 返回响应内容
     * @throws Exception
     */
    public static String updateDoc(String index, String docId, String docJson, String username, String password) throws Exception {
        String url = ELASTICSEARCH_URL + "/" + index + "/_doc/" + docId;
        java.net.http.HttpClient httpClient = createTrustAllHttpClient();
        String authHeader = getBasicAuthHeader(username, password);

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", authHeader)
                .timeout(java.time.Duration.ofSeconds(30))
                .method("POST", java.net.http.HttpRequest.BodyPublishers.ofString(docJson.trim()))
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * 删除文档
     *
     * @param index    索引名
     * @param docId    文档ID
     * @param username es用户名
     * @param password es密码
     * @return 返回响应内容
     * @throws Exception
     */
    public static String deleteDoc(String index, String docId, String username, String password) throws Exception {
        String url = ELASTICSEARCH_URL + "/" + index + "/_doc/" + docId;
        java.net.http.HttpClient httpClient = createTrustAllHttpClient();
        String authHeader = getBasicAuthHeader(username, password);

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Authorization", authHeader)
                .timeout(java.time.Duration.ofSeconds(30))
                .DELETE()
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * 批量写入文档到指定索引（Bulk API）
     *
     * @param index         索引名
     * @param docsJsonArray 数组形式的文档内容（如 List<String> 或 String[]，每个是一个 doc 的 json字符串）
     * @param username      es 用户名
     * @param password      es 密码
     * @return 返回 bulk API 的响应内容
     * @throws Exception
     */
    public static String bulkCreateDoc(String index, java.util.List<String> docsJsonArray, String username, String password) throws Exception {
        String url = ELASTICSEARCH_URL + "/" + index + "/_bulk";

        // 组装 bulk 请求体：每个文档前附一行 {"index":{}}，再跟一行 json
        StringBuilder bulkBodyBuilder = new StringBuilder();
        for (String docJson : docsJsonArray) {
            bulkBodyBuilder.append("{\"index\":{}}\n");
            bulkBodyBuilder.append(docJson.trim()).append("\n");
        }
        String bulkBody = bulkBodyBuilder.toString();

        java.net.http.HttpClient httpClient = createTrustAllHttpClient();
        String authHeader = getBasicAuthHeader(username, password);

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Content-Type", "application/x-ndjson")
                .header("Authorization", authHeader)
                .timeout(java.time.Duration.ofSeconds(60))
                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(bulkBody))
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * 创建跳过SSL验证的HttpClient（仅用于开发环境）
     */
    private static java.net.http.HttpClient createTrustAllHttpClient() throws Exception {
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
        return java.net.http.HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();
    }

    /**
     * 创建基本认证头
     */
    private static String getBasicAuthHeader(String username, String password) {
        String credentials = username + ":" + password;
        return "Basic " + java.util.Base64.getEncoder().encodeToString(credentials.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }


    /**
     * 基于 id 的 search_after 递归分页基准：从首页开始，每页 size 条，打印耗时与命中数。
     */
    private static void benchmarkSearchAfterById(String index, String queryJson, int size, String username, String password) throws Exception {
        // 确保 queryJson 是对象体（闭合由方法内部处理）
        String baseQueryJson = normalizeJsonObject(
                queryJson,
                "{\"query\":{\"match_all\":{}},\"track_total_hits\":true}"
        );

        java.util.List<String> sortFields = List.of("id:asc");
        java.util.List<Object> after = null; // 第一页

        long totalHits = 0;
        int page = 0;
        for (; ; ) {
            page++;
            long t0 = System.nanoTime();
            String resp = queryDocSearchAfter(index, baseQueryJson, sortFields, after, size, username, password);
            long t1 = System.nanoTime();

            // 解析 hits.hits 与最后一条的 sort
            java.util.List<java.util.Map<String, Object>> hits = extractHits(resp);
            int count = hits.size();
            totalHits += count;
            System.out.println("[search_after] page=" + page + ", size=" + size + ", hits=" + count + ", costMs=" + ((t1 - t0) / 1_000_000.0));

            if (count == 0) break;

            java.util.List<Object> nextAfter = extractLastSort(resp);
            if (nextAfter == null || nextAfter.isEmpty()) break;
            after = nextAfter;

            if (count < size) break; // 最后一页
        }
        System.out.println("[search_after] done, totalHits=" + totalHits);
    }

    // 解析 JSON：轻量级解析器，避免引入外部依赖。这里只解析 hits.hits 与每条的 sort。
    @SuppressWarnings("unchecked")
    private static java.util.List<java.util.Map<String, Object>> extractHits(String json) throws Exception {
        java.util.Map<String, Object> obj = parseJsonObject(json);
        Object hitsObj = obj.get("hits");
        if (!(hitsObj instanceof java.util.Map)) return java.util.Collections.emptyList();
        Object arr = ((java.util.Map<String, Object>) hitsObj).get("hits");
        if (!(arr instanceof java.util.List)) return java.util.Collections.emptyList();
        return (java.util.List<java.util.Map<String, Object>>) arr;
    }

    @SuppressWarnings("unchecked")
    private static java.util.List<Object> extractLastSort(String json) throws Exception {
        java.util.Map<String, Object> obj = parseJsonObject(json);
        Object hitsObj = obj.get("hits");
        if (!(hitsObj instanceof java.util.Map)) return java.util.Collections.emptyList();
        Object arr = ((java.util.Map<String, Object>) hitsObj).get("hits");
        if (!(arr instanceof java.util.List)) return java.util.Collections.emptyList();
        java.util.List<java.util.Map<String, Object>> list = (java.util.List<java.util.Map<String, Object>>) arr;
        if (list.isEmpty()) return java.util.Collections.emptyList();
        Object sort = list.get(list.size() - 1).get("sort");
        if (sort instanceof java.util.List) {
            return (java.util.List<Object>) sort;
        }
        return java.util.Collections.emptyList();
    }

    // 极简 JSON 解析：仅支持对象/数组/字符串/数值/布尔/null，满足当前需求
    private static java.util.Map<String, Object> parseJsonObject(String s) throws Exception {
        return (java.util.Map<String, Object>) parse(new JsonCursor(s));
    }

    // --- 下面是一个非常简单的 JSON 解析器（为避免外部依赖） ---
    private static Object parse(JsonCursor c) throws Exception {
        c.skipWs();
        char ch = c.peek();
        if (ch == '{') return parseObj(c);
        if (ch == '[') return parseArr(c);
        if (ch == '"') return parseStr(c);
        if (ch == 't' || ch == 'f') return parseBool(c);
        if (ch == 'n') return parseNull(c);
        return parseNum(c);
    }

    private static java.util.Map<String, Object> parseObj(JsonCursor c) throws Exception {
        java.util.Map<String, Object> map = new java.util.LinkedHashMap<>();
        c.expect('{');
        c.skipWs();
        if (c.peek() == '}') {
            c.expect('}');
            return map;
        }
        for (; ; ) {
            String key = parseStr(c);
            c.skipWs();
            c.expect(':');
            Object val = parse(c);
            map.put(key, val);
            c.skipWs();
            char ch = c.expect(',', '}');
            if (ch == '}') break;
        }
        return map;
    }

    private static java.util.List<Object> parseArr(JsonCursor c) throws Exception {
        java.util.List<Object> list = new java.util.ArrayList<>();
        c.expect('[');
        c.skipWs();
        if (c.peek() == ']') {
            c.expect(']');
            return list;
        }
        for (; ; ) {
            Object val = parse(c);
            list.add(val);
            c.skipWs();
            char ch = c.expect(',', ']');
            if (ch == ']') break;
        }
        return list;
    }

    private static String parseStr(JsonCursor c) throws Exception {
        c.expect('"');
        StringBuilder sb = new StringBuilder();
        while (!c.eof()) {
            char ch = c.next();
            if (ch == '"') break;
            if (ch == '\\') {
                char e = c.next();
                switch (e) {
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':
                        int code = Integer.parseInt(c.next(4), 16);
                        sb.append((char) code);
                        break;
                    default:
                        throw new Exception("Invalid escape: \\" + e);
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private static Boolean parseBool(JsonCursor c) throws Exception {
        if (c.startsWith("true")) {
            c.consume(4);
            return Boolean.TRUE;
        }
        if (c.startsWith("false")) {
            c.consume(5);
            return Boolean.FALSE;
        }
        throw new Exception("Invalid boolean");
    }

    private static Object parseNull(JsonCursor c) throws Exception {
        if (c.startsWith("null")) {
            c.consume(4);
            return null;
        }
        throw new Exception("Invalid null");
    }

    private static Number parseNum(JsonCursor c) throws Exception {
        int start = c.pos;
        while (!c.eof()) {
            char ch = c.peek();
            if ((ch >= '0' && ch <= '9') || ch == '-' || ch == '+' || ch == '.' || ch == 'e' || ch == 'E') {
                c.next();
            } else break;
        }
        String num = c.input.substring(start, c.pos);
        if (num.indexOf('.') >= 0 || num.indexOf('e') >= 0 || num.indexOf('E') >= 0) return Double.parseDouble(num);
        long lv = Long.parseLong(num);
        if (lv >= Integer.MIN_VALUE && lv <= Integer.MAX_VALUE) return (int) lv;
        return lv;
    }

    private static final class JsonCursor {
        final String input;
        int pos = 0;

        JsonCursor(String s) {
            this.input = s;
        }

        boolean eof() {
            return pos >= input.length();
        }

        char peek() {
            return input.charAt(pos);
        }

        char next() {
            return input.charAt(pos++);
        }

        void skipWs() {
            while (!eof()) {
                char c = input.charAt(pos);
                if (c == ' ' || c == '\n' || c == '\r' || c == '\t') pos++;
                else break;
            }
        }

        void consume(int n) {
            pos += n;
        }

        boolean startsWith(String s) {
            return input.startsWith(s, pos);
        }

        char expect(char... options) throws Exception {
            char c = next();
            for (char o : options) if (c == o) return c;
            throw new Exception("Expect one of " + java.util.Arrays.toString(options) + ", got " + c);
        }

        void expect(char must) throws Exception {
            char c = next();
            if (c != must) throw new Exception("Expect '" + must + "' but got '" + c + "'");
        }

        String next(int n) {
            String s = input.substring(pos, pos + n);
            pos += n;
            return s;
        }
    }
}
