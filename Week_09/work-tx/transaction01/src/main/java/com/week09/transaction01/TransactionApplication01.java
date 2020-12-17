package com.week09.transaction01;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("dependency.repo")
@EnableDubbo(scanBasePackages = "com.week09.transaction01.service")
public class TransactionApplication01 {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication01.class, args);
	}

}
