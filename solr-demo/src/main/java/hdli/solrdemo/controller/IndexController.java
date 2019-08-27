package hdli.solrdemo.controller;

import hdli.solrdemo.mapper.BsFlightScheduleMapper;
import hdli.solrdemo.model.FlightScheduleIndexBean;
import hdli.solrdemo.po.BsFlightSchedule;
import hdli.solrdemo.po.BsFlightScheduleExample;
import hdli.solrdemo.service.IndexService;
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
    IndexService indexService;

    @RequestMapping("/create")
    @ResponseBody
    public String create() throws IOException, SolrServerException {
        HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000).withSocketTimeout(60000).build();

        List<BsFlightSchedule> flightList = indexService.getFlightScheduleList("2019-08-19");
        int commintNum = 0;
        if (flightList != null && flightList.size() > 0) {
            for (BsFlightSchedule schedule : flightList) {
                FlightScheduleIndexBean indexBean = new FlightScheduleIndexBean();
                indexBean.setArriveAirport(schedule.getBsfsEndstationCode());
                indexBean.setDepartAirport(schedule.getBsfsStartstationCode());
                indexBean.setEndCityName(schedule.getBsfsEndcityName());
                indexBean.setFlightDate(schedule.getBsfsUpdateDate());
                indexBean.setFlightNo(schedule.getBsfsTransportname());
                indexBean.setFlightType(schedule.getBsfsFlightType());
                indexBean.setPlanArrivalTime(schedule.getBsfsArrivalTime());
                indexBean.setPlanDepartureTime(schedule.getBsfsDepartureTime());
                indexBean.setStartCityName(schedule.getBsfsStartcityName());
                indexBean.setSerialNumber(schedule.getBsfsSerialNumber());

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

    @RequestMapping("/deleteAll")
    @ResponseBody
    public String deletAll() throws IOException, SolrServerException {
        HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).withConnectionTimeout(10000)
                .withSocketTimeout(60000).build();
        solrClient.deleteByQuery("*:*");
        solrClient.commit();
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
