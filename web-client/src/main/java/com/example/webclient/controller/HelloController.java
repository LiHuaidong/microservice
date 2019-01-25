package com.example.webclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 11:31 2018/10/29
 */
@RestController
@RequestMapping("hello")
public class HelloController {

	@RequestMapping("print")
	public String index() {
		return "Hello World";
	}

	@RequestMapping("print2")
	public String print2() {
		return "Hello World2";
	}

	@RequestMapping("print1")
	public String print1() {
		return "Hello World1";
	}

}
