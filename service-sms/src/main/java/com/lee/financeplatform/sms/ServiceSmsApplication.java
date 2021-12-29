package com.lee.financeplatform.sms;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * @author helloyore
 * @createTime 2021年12月29日 19:44:00
 * @Description
 */
@SpringBootApplication
@ComponentScan({"com.lee.financeplatform", "com.lee.common"})
public class ServiceSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class, args);
    }
}