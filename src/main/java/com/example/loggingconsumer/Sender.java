package com.example.loggingconsumer;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

  @Autowired
  private AmqpTemplate rabbitTemplate;

  public void sendToHello() {
    String sendMsg = "send msg " + new Date();
    this.rabbitTemplate.convertAndSend("TestTopicExchange","Hello", sendMsg);
  }

  public void sendToMyQueue(String msg) {
    String sendMsg = msg + "sent to MyQue " + new Date();
    this.rabbitTemplate.convertAndSend("TestTopicExchange","My", sendMsg);
  }

}