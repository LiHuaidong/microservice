package hdli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * Description:
 *
 * @author: hendrix.li
 * @date: 2025-10-20 16:06
 */

public class ElasticsearchIndexManager {

    private static final String ELASTICSEARCH_URL = "https://localhost:9200";
    private static final String MAPPING_FILE_PATH = "src/main/resources/mapping.json";
    private static final String INDEX_NAME = "work-items";
    
    // Elasticsearch 认证信息（请根据实际情况修改）
    private static final String USERNAME = "elastic";  // 默认用户名
    private static final String PASSWORD = "=dipg+nTzeWybM1iYXHM";  // 请修改为实际密码

    /**
     * 创建接受所有 SSL 证书的 TrustManager（仅用于开发环境）
     */
    private static TrustManager[] createTrustAllTrustManager() {
        return new TrustManager[] {
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
    }
    
    /**
     * 创建允许所有 SSL 证书的 HttpClient（仅用于开发环境）
     */
    private static HttpClient createHttpClient() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, createTrustAllTrustManager(), new java.security.SecureRandom());
            return HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("创建 HttpClient 失败", e);
        }
    }
    
    private static final HttpClient httpClient = createHttpClient();
    
    /**
     * 创建基本认证头
     */
    private static String getBasicAuthHeader() {
        String credentials = USERNAME + ":" + PASSWORD;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 发送 HTTP 请求
     */
    private static HttpResponse<String> sendRequest(HttpRequest request) throws Exception {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * 检查索引是否存在
     */
    public static boolean indexExists() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ELASTICSEARCH_URL + "/" + INDEX_NAME))
                    .timeout(Duration.ofSeconds(10))
                    .header("Authorization", getBasicAuthHeader())
                    .GET()
                    .build();
            
            HttpResponse<String> response = sendRequest(request);
            return response.statusCode() == 200;
        } catch (Exception e) {
            System.err.println("检查索引是否存在时出错: " + e.getMessage());
            return false;
        }
    }

    /**
     * 删除索引
     */
    public static void deleteIndex() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ELASTICSEARCH_URL + "/" + INDEX_NAME))
                    .timeout(Duration.ofSeconds(10))
                    .header("Authorization", getBasicAuthHeader())
                    .DELETE()
                    .build();
            
            HttpResponse<String> response = sendRequest(request);
            if (response.statusCode() == 200 || response.statusCode() == 404) {
                System.out.println("索引已删除: " + INDEX_NAME);
            } else {
                System.err.println("删除索引失败，状态码: " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("删除索引时出错: " + e.getMessage());
        }
    }

    /**
     * 创建索引
     */
    public static void createWorkItemIndex() {
        try {
            // 检查索引是否已存在
            if (indexExists()) {
                System.out.println("索引已存在: " + INDEX_NAME);
                return;
            }

            // 读取 mapping.json 文件
            String mappingContent = Files.readString(Paths.get(MAPPING_FILE_PATH), StandardCharsets.UTF_8);
            
            // 创建索引请求
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ELASTICSEARCH_URL + "/" + INDEX_NAME))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/json")
                    .header("Authorization", getBasicAuthHeader())
                    .PUT(HttpRequest.BodyPublishers.ofString(mappingContent))
                    .build();
            
            HttpResponse<String> response = sendRequest(request);
            
            // 输出响应
            System.out.println("状态码: " + response.statusCode());
            System.out.println("响应内容: " + response.body());
            
            if (response.statusCode() == 200 || response.statusCode() == 201) {
                System.out.println("索引创建成功: " + INDEX_NAME);
            } else {
                System.err.println("索引创建失败，状态码: " + response.statusCode());
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("找不到文件: " + MAPPING_FILE_PATH);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.err.println("创建索引时出错: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        createWorkItemIndex();
    }
}