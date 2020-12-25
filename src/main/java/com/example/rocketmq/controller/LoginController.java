package com.example.rocketmq.controller;

import com.example.rocketmq.bean.LoginRequest;
import com.example.rocketmq.bean.RocketmqMessage;
import com.example.rocketmq.rocketmq.RocketmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuanping Zhang
 * @date
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private RocketmqProducer rocketmqProducer;

    @RequestMapping(value = "/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        RocketmqMessage rocketmqMessage = new RocketmqMessage();
        rocketmqMessage.setId("111111");
        rocketmqMessage.setMessage(loginRequest.toString());

        rocketmqProducer.setMessage(rocketmqMessage);
        boolean start = rocketmqProducer.start();
        System.out.println(start);
        return "success";
    }
}
