package com.cloud.feign.rpc;

import org.springframework.stereotype.Component;

/**
 * @ClassName: GetHelloFaceBack
 * @Auther: z1115
 * @Date: 2022/2/19 15:07
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class GetHelloFaceBack implements GetHello {
    @Override
    public String sayHello() {
        return "hello 降级";
    }

    @Override
    public String selectId() {
        return null;
    }
}
