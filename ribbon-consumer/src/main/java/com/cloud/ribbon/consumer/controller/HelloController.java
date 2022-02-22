package com.cloud.ribbon.consumer.controller;
import com.cloud.ribbon.consumer.rpc.GetHello;
import com.cloud.ribbon.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @Autowired
    private GetHello getHello;
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name") String name){
        return helloService.sayHello() + " " + name;
    }

    @GetMapping("/selectId")
    public String selectData(@RequestParam(value = "userId") String usrId){
        String data=getHello.selectId(usrId);
        return data;
    }
}