package com.homework.saturday;

import com.homework.saturday.multi.context.MultiDataSourceRegister;
import com.homework.saturday.multi.service.MytblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Import(MultiDataSourceRegister.class)
@SpringBootApplication
public class SaturdayApplication implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private MytblService mytblService;

	public static void main(String[] args) {
		SpringApplication.run(SaturdayApplication.class, args);
	}


	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		mytblService.batchSave();
		mytblService.show();
	}
}
