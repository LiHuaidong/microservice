package hdli.solrdemo.service;

import hdli.solrdemo.mapper.BsFlightScheduleMapper;
import hdli.solrdemo.po.BsFlightSchedule;
import hdli.solrdemo.po.BsFlightScheduleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class IndexService {

    @Autowired
    BsFlightScheduleMapper bsFlightScheduleMapper;

    public List<BsFlightSchedule> getFlightScheduleList(String flightDate){
//        LocalDate localDate = LocalDate.of(2019, 8, 19);
//        ZoneId zone = ZoneId.systemDefault();
//        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
//        java.util.Date date = Date.from(instant);
//        criteria.andBsfsUpdateDateEqualTo(date);
        List<BsFlightSchedule> flightList = bsFlightScheduleMapper.selectFlightListByDate(flightDate);

        return flightList;
    }
}
