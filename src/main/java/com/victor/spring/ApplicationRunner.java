package com.victor.spring;

import com.victor.spring.database.pool.ConnectionPool;
import com.victor.spring.database.repository.CrudRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {

            ConnectionPool pool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(pool);

            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
