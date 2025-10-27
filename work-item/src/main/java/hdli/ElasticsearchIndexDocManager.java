package hdli;

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

        // String queryJson = "{\"query\":{\"match_all\":{}}}";
        // int from = 0;
        // int size = 10;
        // try {
        //     response = queryDoc(index, queryJson, from, size, username, password);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // System.out.println(response);


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

        System.out.println("All operations completed successfully");
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

}
