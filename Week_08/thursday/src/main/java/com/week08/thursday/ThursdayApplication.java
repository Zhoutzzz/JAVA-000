package com.week08.thursday;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.week08.thursday.repo")
public class ThursdayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThursdayApplication.class, args);
	}

}
