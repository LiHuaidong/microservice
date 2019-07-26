package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.DayOfWeek;
import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

//		LocalDate today = LocalDate.now();
//		DayOfWeek dayOfWeek = today.getDayOfWeek();
//		System.out.println("dayOfWeek = [" + dayOfWeek.getValue() + "]");
	}
}
