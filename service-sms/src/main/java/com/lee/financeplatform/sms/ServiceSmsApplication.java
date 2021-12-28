package com.lee.financeplatform.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author helloyore
 * @createTime 2021年12月28日 22:29:00
 * @Description
 */
@SpringBootApplication
//也能扫瞄到其他包的一些内容
@ComponentScan({"com.lee.financeplatform","com.lee.common"})
public class ServiceSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class, args);
    }
}

