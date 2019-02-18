package hdli.controller;

import com.alibaba.fastjson.JSON;
import hdli.po.Company;
import hdli.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping(value="/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/getCompany", method = { RequestMethod.GET, RequestMethod.POST })
    public String getCompany(@RequestParam String name) {
        List<Company> companyList = clientService.getCompanyByName(name);
        return JSON.toJSONString(companyList);
    }

}
