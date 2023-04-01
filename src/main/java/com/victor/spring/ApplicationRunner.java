package com.victor.spring;

import com.victor.spring.database.pool.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");

        ConnectionPool pool = context.getBean("p1", ConnectionPool.class);
        System.out.println(pool);
    }
}
