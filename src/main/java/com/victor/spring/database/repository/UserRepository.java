package com.victor.spring.database.repository;

import com.victor.spring.database.pool.ConnectionPool;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final ConnectionPool connectionPool;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
