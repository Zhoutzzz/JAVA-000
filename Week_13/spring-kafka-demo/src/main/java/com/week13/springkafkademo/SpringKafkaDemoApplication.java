package com.week13.springkafkademo;

import com.week13.springkafkademo.message.Consumers;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@EnableKafka
public class SpringKafkaDemoApplication {

	@Autowired
	Consumers consumers;

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaDemoApplication.class, args);
	}

	@PostMapping("/send/{msg}")
	public void send(@PathVariable String msg) {
		consumers.execute(msg);
	}


	@Bean
	public KafkaAdmin admin(KafkaProperties properties){
		KafkaAdmin admin = new KafkaAdmin(properties.buildAdminProperties());
		admin.setFatalIfBrokerNotAvailable(true);
		return admin;
	}

	@Bean
	public NewTopic topic1() {
		return TopicBuilder.name("giao")
				.partitions(2)
				.replicas(3)
//				.compact()
//                .config(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE)
				.build();
	}

//	@Bean
//	public KafkaTemplate<String, String> newTemplate() {
//		return new KafkaTemplate<>(null);
//	}
}
