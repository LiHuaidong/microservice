package hdli.mapper;

import hdli.model.CompanyModel;
import hdli.model.EmployeeModel;
import hdli.po.Company;
import hdli.po.CompanyExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CompanyMapper {
    int countByExample(CompanyExample example);

    int deleteByExample(CompanyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    List<Company> selectByExample(CompanyExample example);

    Company selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByExample(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    CompanyModel getCompanyWithEmployee(@Param("id") Integer id);

    List<EmployeeModel> getEmployeeList(@Param("id") Integer companyId);
}