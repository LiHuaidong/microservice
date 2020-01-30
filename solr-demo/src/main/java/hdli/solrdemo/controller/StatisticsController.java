package hdli.solrdemo.controller;

import hdli.solrdemo.service.IndexService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("statistics")
public class StatisticsController {

    private Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    private static final String SOLR_URL = "http://localhost:8983/solr/testcore";

    @RequestMapping("/facetQueryByDepartAirport")
    @ResponseBody
    public String facetQueryByDepartAirport(@RequestParam(name = "arriveAirport") String arriveAirport)
            throws IOException, SolrServerException {
        if (StringUtils.isEmpty(arriveAirport)) {
            return "arriveAirport is None";
        }

        HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();

        SolrQuery query = new SolrQuery();
        query.setQuery("arriveAirport:" + arriveAirport + "*");
        query.setFacet(true);
        query.addFacetField("departAirport");

        QueryResponse response = solrClient.query(query);
        List<FacetField> facets = response.getFacetFields();//返回的facet列表
        StringBuffer result = new StringBuffer();
        for (FacetField facet : facets) {
            result.append(facet.getName());
            result.append(",");
            List<FacetField.Count> counts = facet.getValues();

            for (FacetField.Count count : counts) {
                result.append(count.getName()).append(":").append(count.getCount()).append("\r\n");
            }
        }
        return result.toString();
    }

    @RequestMapping("/queryByArriveAirportAndHighlightFlightNo")
    @ResponseBody
    public String queryByArriveAirportAndHighlightFlightNo(@RequestParam(name = "arriveAirport") String arriveAirport)
            throws IOException, SolrServerException {
        if (StringUtils.isEmpty(arriveAirport)) {
            return "arriveAirport is None";
        }

        HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();

        SolrQuery query = new SolrQuery();
        query.setQuery("arriveAirport:" + arriveAirport + "*");
        query.setHighlight(true);
        query.addHighlightField("flightNo");
        query.setHighlightSimplePre("<red>");
        query.setHighlightSimplePost("</red>");

        QueryResponse response = solrClient.query(query);
        SolrDocumentList documents = response.getResults();

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        for(SolrDocument doc : documents) {
            logger.info(String.valueOf(doc.get("id")));
            List<String> hightDocs = highlighting.get(doc.get("id")).get("flightNo");
            if (hightDocs != null)
                System.out.println("高亮显示的商品名称：" + hightDocs.get(0));
            else {
                System.out.println(doc.get("flightNo"));
            }
        }
        return "SUCCESS";
    }

}
