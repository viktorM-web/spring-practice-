package com.victor.spring;

import com.victor.spring.config.ApplicationConfiguration;
import com.victor.spring.database.pool.ConnectionPool;
import com.victor.spring.database.repository.CrudRepository;
import com.victor.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();

            ConnectionPool pool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(pool);

            var companyService = context.getBean(CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}
