package hdli.services;

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

}
