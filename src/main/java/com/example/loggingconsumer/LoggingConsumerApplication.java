package com.example.loggingconsumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LoggingConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoggingConsumerApplication.class, args);
  }
  @Bean
  TopicExchange createExchange() {
    return new TopicExchange("testTopicExchange");
  }
  
  @Bean
  public Queue helloQueue() {
    return new Queue("helloQueue");
  }
  
  @Bean
  public Queue myQueue() {
    return new Queue("MyQueue");
  }
  
  @Bean
  Binding createHelloQueBinding(Queue helloQueue, TopicExchange exchange) {
    return BindingBuilder.bind(helloQueue).to(exchange).with("hello");
  }
  
  @Bean
  Binding createMyQueBinding(Queue myQueue, TopicExchange exchange) {
    return BindingBuilder.bind(myQueue).to(exchange).with("My");
  }
  
  @RabbitHandler
  @RabbitListener(queues="MyQueue")
  public void handle(String msg) {

    System.out.println("Received in MyQueue: " + msg);
  }

  @RabbitHandler
  @RabbitListener(queues="helloQueue")
  public void handleHello(String msg) {

    System.out.println("Received in hellQueque: " + msg);
  }
}