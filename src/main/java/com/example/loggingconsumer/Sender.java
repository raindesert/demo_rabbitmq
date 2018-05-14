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
    String sendMsg = "hello1 " + new Date();
    this.rabbitTemplate.convertAndSend("helloQueue", sendMsg);
  }

  public void sendToMyQueue() {
    String sendMsg = "helloToMyQue " + new Date();
    this.rabbitTemplate.convertAndSend("MyQueue", sendMsg);
  }

}