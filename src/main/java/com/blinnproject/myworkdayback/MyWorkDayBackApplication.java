package com.blinnproject.myworkdayback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "com.blinnproject.myworkdayback")
@SpringBootApplication
public class MyWorkDayBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWorkDayBackApplication.class, args);
	}

}
