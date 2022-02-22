package com.cloud.feign.controller;

import com.cloud.feign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;
    @GetMapping("/hi")
    public String sayHello(String name){
        return helloService.sayHello();
    }


    @GetMapping("/selectId")
    public  String selectId(@RequestParam(value = "userId") String usrId){
        String data=helloService.selectId(usrId);
        return data;
    }
}