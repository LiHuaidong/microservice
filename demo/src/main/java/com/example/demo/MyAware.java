package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:27 2019/7/29
 */
@Component
public class MyAware implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void print() {
		System.out.println("123");
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}

	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
}
