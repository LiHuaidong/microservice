package hdli.solrdemo.controller;

import hdli.solrdemo.mapper.BsFlightScheduleMapper;
import hdli.solrdemo.po.BsFlightSchedule;
import hdli.solrdemo.po.BsFlightScheduleExample;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    BsFlightScheduleMapper bsFlightScheduleMapper;

    @RequestMapping("/create")
    public void main() throws IOException, SolrServerException {
        final String solrUrl = "http://localhost:8983/solr/testcore";
        HttpSolrClient solrServer = new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();

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
                SolrInputDocument document = createDocument(schedule);
                solrServer.add(document);

                if (commintNum % 100 == 0) {
                    solrServer.commit();
                }
            }
        }
    }

    public SolrInputDocument createDocument(BsFlightSchedule bsFlightSchedule) {
        // 注意: id的域不能少
        SolrInputDocument document = new SolrInputDocument();
        document.addField("", "");
        document.addField("", "");
        document.addField("", "");
        document.addField("", "");
        document.addField("", "");
        document.addField("", "");
        document.addField("", "");
        document.addField("", "");
        document.addField("", "");
        document.addField("", "");

        return document;
    }

}
