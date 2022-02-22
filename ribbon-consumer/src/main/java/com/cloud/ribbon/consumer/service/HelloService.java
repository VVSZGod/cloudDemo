package com.cloud.ribbon.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    public String sayHello() {
        //return restTemplate.getForObject("http://ribbon-provider/hello?name=zhangtaifeng",String.class);
        return "Hello";
        //return restTemplate.getForObject("https://yunxim.com:9002/hs/selectOnline?usrid=301&orgid=YUNXIMHS",String.class);
        //restTemplate.getForObject("http://ribbon-provider/hello?name=zhangtaifeng",String.class,Map<String,Object> map);
    }

}
