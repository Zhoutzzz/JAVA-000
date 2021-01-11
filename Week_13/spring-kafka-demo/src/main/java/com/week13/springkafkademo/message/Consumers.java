package com.week13.springkafkademo.message;

import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.util.*;

/**
 * @author zhoutzzz
 */

@Service
public class Consumers {

    private final KafkaTemplate<String, String> template;

    @Autowired
    public Consumers(KafkaTemplate template) {
        this.template = template;
    }

    public void execute(String msg) {
//        template.send("shuaige", "qiekenao", "galigeigei");
        template.send("giao", msg);
    }

    @KafkaListener(topics = "giao")
    public void listen(String mes) {
        System.out.println(mes);
    }
}
