package com.victor.spring;

import com.victor.spring.config.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context.getBeanDefinitionCount());
        System.out.println(context.getBean("pool2"));
        System.out.println(context.getBean(DatabaseProperties.class));
    }
}
