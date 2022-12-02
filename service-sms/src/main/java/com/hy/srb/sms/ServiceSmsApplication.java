package com.hy.srb.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
@ComponentScan({"com.hy.srb", "com.hy.common"})
public class ServiceSmsApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class, args);
    }
}