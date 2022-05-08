package com.example.L08springbootmvcannotations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class L08SpringBootMvcAnnotationsApplication {

	private static Logger logger = LoggerFactory.getLogger(L08SpringBootMvcAnnotationsApplication.class);


	public static void main(String[] args) {


		SpringApplication.run(L08SpringBootMvcAnnotationsApplication.class, args);

		logger.trace("Starting application in TRACE log level after spring start");
		logger.debug("Starting application in DEBUG log level after spring start");
		logger.info("Starting application in INFO log level after spring start");
		logger.warn("Starting application in WARN log level after spring start");
		logger.error("Starting application in ERROR log level after spring start");



	}

}
