package com.cloud.feign.service;

import com.cloud.feign.rpc.GetHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class HelloService {
    @Autowired
    private GetHello getHello; //注入rpc

    public String sayHello(){
        return getHello.sayHello(); // 提供一个hello World
    }

  /*  public Map selectId(){
        return getHello.selectId(); // 提供一个hello World
    }*/

    @Autowired
    RestTemplate restTemplate;

    public String selectId(String usrid) {
        //return restTemplate.getForObject("http://ribbon-consumer/selectId?usrid="+usrid,String.class);
        //return restTemplate.getForObject("https://yunxim.com:9002/hs/selectOnline?usrid=301&orgid=YUNXIMHS",String.class);
        //restTemplate.getForObject("http://ribbon-provider/hello?name=zhangtaifeng",String.class,Map<String,Object> map);
        return "返回消费者"+usrid;
    }
}
