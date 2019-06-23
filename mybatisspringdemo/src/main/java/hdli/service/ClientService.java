package hdli.service;

import hdli.mapper.CompanyMapper;
import hdli.po.Company;
import hdli.po.CompanyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    CompanyMapper companyMapper;

    public List<Company> getCompanyByName(String name) {
        CompanyExample example = new CompanyExample();
        CompanyExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);

        List<Company> companyList = companyMapper.selectByExample(example);

        return companyList;
    }

    public String createCompanyByName(String name) {
        Company company = new Company();
        company.setName(name);
        company.setType("02");
        int updated = companyMapper.insert(company);
        if (updated < 1) {
            return "FAIL";
        }
        return "SUCCESS";
    }

}
