package com.example.loggingconsumer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoggingConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoggingConsumerApplication.class, args);
  }
  
  @Bean
  public Queue helloQueue() {
    return new Queue("helloQueue");
  }
  
  @Bean
  public Queue myQueye() {
    return new Queue("MyQueue");
  }
  
  @RabbitHandler
  @RabbitListener(queues="MyQueue")
  public void handle(String msg) {

    System.out.println("Received: " + msg);
  }

  @RabbitHandler
  @RabbitListener(queues="helloQueue")
  public void handleHello(String msg) {

    System.out.println("Received2: " + msg);
  }
}