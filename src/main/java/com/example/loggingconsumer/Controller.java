package com.example.loggingconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class Controller {

  @Autowired
  private Sender sender;

  @GetMapping("/hello")
  public String hello() {
    sender.send();
    return "msg sent";
  }

  @GetMapping("/send")
  public String sendToMyQueue() {
    sender.sendToMyQueue();
    return "msg sent";
  }
}