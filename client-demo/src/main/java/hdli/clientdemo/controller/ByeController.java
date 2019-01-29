package hdli.clientdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 18:01 2018/11/10
 */
@Controller
@RequestMapping("bye")
public class ByeController {

	@RequestMapping("/print1")
	@ResponseBody
	public String index() {
		return "Bye-World";
	}

}