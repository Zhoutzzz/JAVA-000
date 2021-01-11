package com.week13.activitymq.test;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author zhoutzzz
 */

@Component
public class MqTest {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void go(Email msg) {
        System.out.println("received: " + msg);
    }
}
