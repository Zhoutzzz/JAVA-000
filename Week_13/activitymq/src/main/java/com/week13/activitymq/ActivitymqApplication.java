package com.week13.activitymq;

import com.week13.activitymq.test.Email;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableJms
public class ActivitymqApplication {

	@Autowired
	Environment env;

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ActivitymqApplication.class, args);
		JmsTemplate bean = run.getBean(JmsTemplate.class);
		Destination mailbox = new ActiveMQQueue("mailbox");
		Destination mailboxTopic = new ActiveMQTopic("mailboxTopic");
		//点对点
//		bean.convertAndSend(mailbox, new Email("info@exam.com", "ptp:queue---->hello"));
		//发布订阅
		bean.convertAndSend(mailboxTopic, new Email("info@exam.com", "pubsub:topic---->hello"));
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(env.getProperty("spring.activemq.broker-url"));
		factory.setUserName(env.getProperty("spring.activemq.user"));
		factory.setPassword(env.getProperty("spring.activemq.password"));
		return factory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		Map<String, Class<?>> typeMapping = new HashMap<>(4);
		typeMapping.putIfAbsent("_type", Email.class);
		converter.setTypeIdMappings(typeMapping);
		return converter;
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
		JmsTemplate jmsMessagingTemplate = new JmsTemplate(connectionFactory);
		jmsMessagingTemplate.setMessageConverter(converter);
		return jmsMessagingTemplate;
	}

	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
													DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		return factory;
	}
}
