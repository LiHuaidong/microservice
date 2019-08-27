package hdli.solrdemo.controller;

import hdli.solrdemo.mapper.BsFlightScheduleMapper;
import hdli.solrdemo.model.FlightScheduleIndexBean;
import hdli.solrdemo.po.BsFlightSchedule;
import hdli.solrdemo.po.BsFlightScheduleExample;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.server.support.SolrClientUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("index")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    private static final String SOLR_URL = "http://localhost:8983/solr/testcore";

    @Autowired
    BsFlightScheduleMapper bsFlightScheduleMapper;

    @RequestMapping("/create")
    @ResponseBody
    public String create() throws IOException, SolrServerException {
        HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000).withSocketTimeout(60000).build();

        BsFlightScheduleExample example = new BsFlightScheduleExample();
        BsFlightScheduleExample.Criteria criteria = example.createCriteria();

        LocalDate localDate = LocalDate.of(2019, 8, 19);

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
        criteria.andBsfsUpdateDateEqualTo(date);

        int commintNum = 0;
        List<BsFlightSchedule> flightList = bsFlightScheduleMapper.selectByExample(example);
        if (flightList != null && flightList.size() > 0) {
            for (BsFlightSchedule schedule : flightList) {
                FlightScheduleIndexBean indexBean = new FlightScheduleIndexBean();
                BeanUtils.copyProperties(schedule, indexBean);

                SolrInputDocument document = solrClient.getBinder().toSolrInputDocument(indexBean);
                solrClient.add(document);
                commintNum++;
                if (commintNum % 100 == 0) {
                    solrClient.commit();
                }
            }
        }
        return "SUCCESS";
    }

    @RequestMapping("/updateActualTime")
    @ResponseBody
    public String updateActualTime(
            @RequestParam(name = "actualDepartureTime") String actualDepartureTime,
            @RequestParam(name = "actualArriveTime") String actualArriveTime,
            @RequestParam(name = "docId") String docId) throws IOException, SolrServerException {
        if(StringUtils.isEmpty(actualDepartureTime)) {
            return "actualDepartureTime is None";
        }
        if(StringUtils.isEmpty(actualArriveTime)) {
            return "actualArriveTime is None";
        }
        if (StringUtils.isEmpty(docId)) {
            return "docId is None";
        }

        HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", docId);

        Map<String, Object> operActualArriveTime = new HashMap<>();
        operActualArriveTime.put("set", actualArriveTime);

        Map<String, Object> operActualDepartTime = new HashMap<>();
        operActualDepartTime.put("set", actualDepartureTime);

        doc.addField("actualDepartureTime", operActualArriveTime);
        doc.addField("actualArriveTime", operActualDepartTime);

        solrClient.add(doc);
        solrClient.commit();

        return "SUCCESS";
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(@RequestParam(name = "docId") String docId)
            throws IOException, SolrServerException {
        if (StringUtils.isEmpty(docId)) {
            return "docId is None";
        }
        HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();
        solrClient.deleteById(docId);
        return "SUCCESS";
    }

    @RequestMapping("/queryByFlightNo")
    @ResponseBody
    public String queryByFlightNo(@RequestParam(name = "flightNo") String flightNo) throws IOException, SolrServerException {
        if (StringUtils.isEmpty(flightNo)) {
            return "flightNo is None";
        }

        HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();

        SolrQuery query = new SolrQuery();
        query.setQuery("flightNo:" + flightNo + "*");

        QueryResponse response = solrClient.query(query);
        SolrDocumentList list = response.getResults();
        return list.toString();
    }

}
