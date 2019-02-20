package hdli.controller;

import com.alibaba.fastjson.JSON;
import hdli.common.GlobalResponse;
import hdli.po.Company;
import hdli.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientService clientService;

    @Transactional
    @RequestMapping(value = "/getCompany", method = { RequestMethod.GET, RequestMethod.POST })
    public Object getCompany(@RequestParam(name = "type") String type,
            @RequestParam(name = "name", required = false) String name) {
        LOGGER.info("begin to getCompany, type is {}, name is {}", type, name);
        List<Company> companyList = clientService.getCompanyByParam(type, name);
        GlobalResponse response = new GlobalResponse();
        response.setCode(1);
        response.setTipMessage("SUCCESS");
        response.setData(companyList);
        return JSON.toJSON(response);
    }

    @Transactional
    @RequestMapping(value = "/createCompany", method = { RequestMethod.GET, RequestMethod.POST })
    public Object createCompany(@RequestParam String name, @RequestParam String type) {
        LOGGER.info("begin to createCompany, type is {}, name is {}", type, name);
        int result = clientService.createCompanyByParam(name, type);
        GlobalResponse response = new GlobalResponse();
        if (result > 0) {
            response.setCode(1);
            response.setTipMessage("SUCCESS");
        } else {
            response.setCode(0);
            response.setTipMessage("FAIL");
        }
        return JSON.toJSON(response);
    }

}
