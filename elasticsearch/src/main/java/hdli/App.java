package hdli;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    // 获取ElasticSearcch Client对象
    private Client getElasticSearchClient() throws UnknownHostException {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("172.16.255.142"), 9300));
        return client;
    }

    private void createIndex(TransportClient client) throws IOException, InterruptedException, ExecutionException {
        client.admin().indices().prepareCreate("book").execute().actionGet();

        XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .startObject("article")
                .startObject("properties")
                .startObject("title").field("type", "String").field("store", "yes").endObject()
                .startObject("year").field("type", "integer").field("store", "yes").endObject()
                .startObject("content").field("type", "text").field("store", "yes").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()
                .endObject().endObject().endObject();

        PutMappingRequest mapping = Requests.putMappingRequest("book").type("article").source(builder);
        client.admin().indices().putMapping(mapping).actionGet();
    }

    public static void add(TransportClient client) throws ExecutionException, InterruptedException {
        String json = "{\"title\":\"国歌\", \"year\":1885,\"content\":\"起来不愿做奴隶的人民，把我们的血肉筑成我们新的长城北上广南京天安门，中华民族到了最危险的时候，每个人们都。。。。\"}";
        IndexResponse indexResponse = client.prepareIndex("book", "article", "1")
                .setSource(json, XContentType.JSON)
                .execute().get();
    }

    public static void searchAll(TransportClient client) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("content", "天安门长城").analyzer("ik_max_word")
                .operator(Operator.AND);
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
        highlightBuilder.preTags("<span style=\"color:red\">");
        highlightBuilder.postTags("</span>");
        SearchResponse searchResponse = client.prepareSearch("book").setTypes("article").highlighter(highlightBuilder)
                .setQuery(queryBuilder).execute().actionGet();

        SearchHit[] hits = searchResponse.getHits().getHits();
        System.out.println(hits.length);
        for (SearchHit hit : hits) {
            Map<String, HighlightField> result = hit.getHighlightFields();

            HighlightField titleField = result.get("content");
            //取得定义的高亮标签
            Text[] titleTexts = titleField.fragments();
            //为title串值增加自定义的高亮标签
            StringBuffer title = new StringBuffer("");
            for (Text text : titleTexts) {
                title.append(text);
            }
            System.out.println(title.toString());
        }
    }

}
