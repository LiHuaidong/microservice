package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author: Lihuaidong
 * @Date: Created at 17:28 2019/7/29
 */
@Component
public class MyRunner implements ApplicationRunner {
	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming application arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {

		System.out.println("hdli = my runner");
		ApplicationContext context = MyAware.getApplicationContext();
		System.out.println("hdli = my runner");
	}
}
