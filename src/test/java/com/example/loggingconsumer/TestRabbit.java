package com.example.loggingconsumer;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestRabbit {

  @Test
  public void testSend() {
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setUri("amqp://guest:guest@localhost:5672");
      Connection conn = factory.newConnection();
      Channel channel = conn.createChannel();  
      channel.queueDeclare("MyQueue", false, false, false, null);  
      String message = "{\"name\":\"test\"}";  
      channel.basicPublish("", "MyQueue", null, message.getBytes("UTF-8")); 
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
