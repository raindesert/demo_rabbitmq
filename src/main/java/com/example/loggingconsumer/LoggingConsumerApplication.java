package com.example.loggingconsumer;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class LoggingConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoggingConsumerApplication.class, args);
  }

  @RabbitHandler
  @RabbitListener(queues="MyQueue")
  public void handle(String person) {
    String[] listStr = person.split(",");
    byte[] listByte = new byte[listStr.length];
    for (int i = 0; i < listStr.length; i++) {
      listByte[i] = new Byte(listStr[i]);
    }
    try {
      person = new String(listByte, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    System.out.println("Received: " + person);
  }

}