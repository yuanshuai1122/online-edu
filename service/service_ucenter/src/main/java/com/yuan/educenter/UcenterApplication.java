package com.yuan.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: yyss
 * @create: 2022-02-13 21:53
 **/
@ComponentScan(basePackages = {"com.yuan"})
@SpringBootApplication
@MapperScan("com.yuan.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
