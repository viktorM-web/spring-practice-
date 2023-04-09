package com.victor.spring.config;

import com.victor.spring.database.pool.ConnectionPool;
import com.victor.spring.database.repository.UserRepository;
import com.victor.web.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
public class ApplicationConfiguration {

        @Bean("pool2")
        @Scope(SCOPE_SINGLETON)
        public ConnectionPool pool2(@Value("${db.username}") String username){
                return new ConnectionPool(username, 20);
        }

        @Bean()
        public ConnectionPool pool3(){
                return new ConnectionPool("test-pool", 20);
        }

        @Bean
        @Profile("prod|web")
        public UserRepository userRepository2(ConnectionPool pool2) {
                return new UserRepository(pool2);
        }

        @Bean
        public UserRepository userRepository3() {
                ConnectionPool pool1 = pool3();
                ConnectionPool pool2 = pool3();
                ConnectionPool pool3 = pool3();
                return new UserRepository(pool3());
        }
}
