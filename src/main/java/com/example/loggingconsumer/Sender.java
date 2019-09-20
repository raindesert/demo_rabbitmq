package com.example.loggingconsumer;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

  @Autowired
  private AmqpTemplate rabbitTemplate;

  public void send() {
    String sendMsg = "send msg " + new Date();
    this.rabbitTemplate.convertAndSend("testTopicExchange","hello", sendMsg);
  }

  public void sendToMyQueue() {
    String sendMsg = "helloToMyQue msg " + new Date();
    this.rabbitTemplate.convertAndSend("testTopicExchange","My", sendMsg);
  }

}