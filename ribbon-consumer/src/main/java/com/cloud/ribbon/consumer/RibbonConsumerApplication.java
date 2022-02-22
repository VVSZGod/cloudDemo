package com.cloud.ribbon.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableDiscoveryClient   //注册服务
@EnableFeignClients      //发现服务
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }

    @Bean
    //@Autowired
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


  /*  @Resource
    DataSource dataSource;

    @Bean
    protected Connection getConn() {
        Connection conn=null;
        try {
            conn= dataSource.getConnection();
        } catch (Exception e) {

        }
        return conn;
    }*/
}
