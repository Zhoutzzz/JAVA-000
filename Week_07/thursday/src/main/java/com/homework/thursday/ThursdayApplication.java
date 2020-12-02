package com.homework.thursday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ThursdayApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ThursdayApplication.class, args);
		BatchInsert bean = run.getBean(BatchInsert.class);
		try {
			bean.go();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Bean
	public BatchInsert get() {
		return new BatchInsert();
	}
}
