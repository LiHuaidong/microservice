package hdli.solrdemo.mapper;

import hdli.solrdemo.po.BsFlightSchedule;
import hdli.solrdemo.po.BsFlightScheduleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BsFlightScheduleMapper {
    int countByExample(BsFlightScheduleExample example);

    int deleteByExample(BsFlightScheduleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BsFlightSchedule record);

    int insertSelective(BsFlightSchedule record);

    List<BsFlightSchedule> selectByExample(BsFlightScheduleExample example);

    BsFlightSchedule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BsFlightSchedule record, @Param("example") BsFlightScheduleExample example);

    int updateByExample(@Param("record") BsFlightSchedule record, @Param("example") BsFlightScheduleExample example);

    int updateByPrimaryKeySelective(BsFlightSchedule record);

    int updateByPrimaryKey(BsFlightSchedule record);
}