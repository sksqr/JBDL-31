package com.example.L07springbootdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class L07SpringBootDemoApplication {

	private static Logger logger = LoggerFactory.getLogger(L07SpringBootDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(L07SpringBootDemoApplication.class, args);
		logger.info("Started Product Service");
		logger.error("Started Product Service Error Level");
	}

}
