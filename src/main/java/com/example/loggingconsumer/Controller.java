package com.example.loggingconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/rabbit")
public class Controller {

  @Autowired
  private Sender sender;

  @ApiOperation(value="hello", notes="send hello msg")
  @GetMapping("/hello")
  public String hello() {
    sender.send();
    return "msg sent";
  }

  @ApiOperation(value="send msg", notes="send specific msg")
  @ApiImplicitParams({      
      @ApiImplicitParam(name = "msg", value = "message sent to my que", required = true, dataType = "String")
  })
  @GetMapping("/send")
  public String sendToMyQueue(@RequestParam String msg) {
    sender.sendToMyQueue(msg);
    return "msg sent";
  }
}