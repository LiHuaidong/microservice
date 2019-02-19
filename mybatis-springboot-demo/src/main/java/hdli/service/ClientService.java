package hdli.service;

import hdli.common.GlobalException;
import hdli.mapper.CompanyMapper;
import hdli.po.Company;
import hdli.po.CompanyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    CompanyMapper companyMapper;

    public List<Company> getCompanyByParam(String type, String name) {
        CompanyExample example = new CompanyExample();
        CompanyExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }

        if(!StringUtils.isEmpty(name)) {
            criteria.andNameEqualTo(name);
        }
        List<Company> companyList = companyMapper.selectByExample(example);

        return companyList;
    }

    public String createCompanyByParam(String name, String type) {
        Company company = new Company();
        company.setName(name);
        company.setType(type);
        int updated = companyMapper.insert(company);
        if (updated < 1) {
            return "FAIL";
        } else{
          throw new GlobalException("测试回滚");
        }
        // return "SUCCESS";
    }

}
