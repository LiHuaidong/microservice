package hdli.controller;

import com.alibaba.fastjson.JSON;
import hdli.po.Company;
import hdli.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Transactional
    @RequestMapping(value = "/getCompany", method = { RequestMethod.GET, RequestMethod.POST })
    public Object getCompany(@RequestParam(name = "type") String type,
            @RequestParam(name = "name", required = false) String name) {
        List<Company> companyList = clientService.getCompanyByParam(type, name);
        return JSON.toJSON(companyList);
    }

    @Transactional
    @RequestMapping(value = "/createCompany", method = { RequestMethod.GET, RequestMethod.POST })
    public Object createCompany(@RequestParam String name, @RequestParam String type) {
        return JSON.toJSON(clientService.createCompanyByParam(name, type));
    }

}
