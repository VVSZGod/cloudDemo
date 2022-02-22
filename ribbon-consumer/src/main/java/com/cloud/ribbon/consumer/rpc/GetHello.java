package com.cloud.ribbon.consumer.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "feign-consumer",fallback = GetHelloFaceBack.class)
public interface GetHello {
    @RequestMapping(value = "/hello?name=feign",method = RequestMethod.GET)
    public String sayHello();

    @RequestMapping(value = "/selectId",method = RequestMethod.GET)
    public String selectId(@RequestParam(value = "userId") String usrId);
}