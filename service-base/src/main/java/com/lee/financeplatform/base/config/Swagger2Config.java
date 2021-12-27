package com.lee.financeplatform.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

/**
 * @author helloyore
 * @createTime 2021年12月23日 20:53:00
 * @Description
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    //Swagger文件配置 Docket是Swagger的文档对象
    //加了Bean注解后会自动的将其加到对应程序的上下文当中
    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo()) //配置文档描述 因为内容多，单独拉出来写
                //做过滤器处理，将管理页面的接口与网站前台接口分离
                .select() //通过过滤器来过滤路径
                .paths(Predicates.and(PathSelectors.regex("/admin/.*"))) //path写具体的过滤过程 路径选择器 通过正则表达式来过滤
                .build();
    }
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())//做过滤器处理，将管理页面的接口与网站前台接口分离
                .select() //通过过滤器来过滤路径
                .paths(Predicates.and(PathSelectors.regex("/api/.*"))) //path写具体的过滤过程 路径选择器 通过正则表达式来过滤
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("金融平台后台管理系统API文档")
                .description("该文档描述了【小白】金融平台后台管理系统的各个模块的接口的调用方式")
                .version("0.1")
                .contact(new Contact("helloYore", "Http://helloyore.com","helloyore@outlook.com"))
                .build();
    }
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("金融平台网站前台API文档")
                .description("该文档描述了【小白】金融平台网站前台的各个模块的接口的调用方式")
                .version("0.1")
                .contact(new Contact("helloYore", "Http://helloyore.com","helloyore@outlook.com"))
                .build();
    }
}
