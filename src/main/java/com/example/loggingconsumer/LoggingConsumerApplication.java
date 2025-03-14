package com.example.loggingconsumer;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
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
    return new TopicExchange("TestTopicExchange");
  }
  
  @Bean
  public Queue helloQueue() {
    return new Queue("HelloQueue");
  }
  
  @Bean
  public Queue myQueue() {
    return new Queue("MyQueue");
  }
  
  @Bean
  Binding createHelloQueBinding(Queue helloQueue, TopicExchange exchange) {
    return BindingBuilder.bind(helloQueue).to(exchange).with("Hello");
  }
  
  @Bean
  Binding createMyQueBinding(Queue myQueue, TopicExchange exchange) {
    return BindingBuilder.bind(myQueue).to(exchange).with("My");
  }
  
  @RabbitHandler
  @RabbitListener(queues="MyQueue")
  public void handleMy(String msg,Message m, Channel channel) throws Exception{
    System.out.println("Received in MyQueue: " + msg+"!");
    channel.basicAck(m.getMessageProperties().getDeliveryTag(),false);
  }

  @RabbitHandler
  @RabbitListener(queues="HelloQueue")
  public void handleHello(Message msg, Channel channel) throws Exception{
    System.out.println("Received in HelloQueue: " + msg);
    channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
  }
}