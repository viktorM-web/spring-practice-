package com.victor.spring.database.repository;

import com.victor.spring.database.pool.ConnectionPool;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Scope(SCOPE_PROTOTYPE)
@Repository
public class UserRepository {

    private final ConnectionPool connectionPool;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
