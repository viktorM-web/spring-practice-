package com.victor.spring.database.repository;

import com.victor.spring.database.bpp.InjectBean;
import com.victor.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    private ConnectionPool connectionPool;
}
