package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:31 2018/10/29
 */
@RestController
public class HelloController {

	@RequestMapping
	public String index() {
		return "Hello World";
	}

}
