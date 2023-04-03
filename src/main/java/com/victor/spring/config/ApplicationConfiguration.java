package com.victor.spring.config;

import com.victor.spring.database.pool.ConnectionPool;
import com.victor.spring.database.repository.CrudRepository;
import com.victor.spring.database.repository.UserRepository;
import com.victor.web.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;
import static org.springframework.context.annotation.ComponentScan.*;

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.victor.spring",
        useDefaultFilters = false,
        includeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = Component.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
                @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
        })
public class ApplicationConfiguration {

        @Bean("pool2")
        @Scope(SCOPE_SINGLETON)
        public ConnectionPool pool2(@Value("${db.username}") String username){
                return new ConnectionPool(username, 20);
        }

        @Bean
        public UserRepository userRepository2(ConnectionPool pool2) {
                return new UserRepository(pool2);
        }
}
