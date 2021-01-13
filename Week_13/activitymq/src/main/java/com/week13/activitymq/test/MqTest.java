package com.week13.activitymq.test;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author zhoutzzz
 */

@Component
public class MqTest {

    @JmsListener(destination = "mailboxTopic", containerFactory = "myFactory")
    public void go(Email msg) {
        System.out.println("received: " + msg);
    }


    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void go1(Email msg) {
        System.out.println("received: " + msg);
    }
}
