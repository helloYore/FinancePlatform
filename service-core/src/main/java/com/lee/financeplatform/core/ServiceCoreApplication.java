package com.lee.financeplatform.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author helloyore
 * @createTime 2021年12月23日 19:55:00
 * @Description
 */
@SpringBootApplication
//也能扫瞄到其他包的一些内容
@ComponentScan({"com.lee.financeplatform"})
public class ServiceCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class, args);
    }
}
