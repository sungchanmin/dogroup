package com.dogroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application 클래스
 * @author NYK
 *
 */
@SpringBootApplication
public class DogroupApplication {
	
	private static Logger log = LoggerFactory.getLogger(DogroupApplication.class);
	
	public static void main(String[] args) {
		log.info("DoGroup Spring Container 구동 시작");
		SpringApplication.run(DogroupApplication.class, args);
	}

}
