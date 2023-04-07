package com.victor.spring.database.repository;

import com.victor.spring.bpp.Auditing;
import com.victor.spring.bpp.Transaction;
import com.victor.spring.database.entity.Company;
import com.victor.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Slf4j
@Repository
@Scope(SCOPE_PROTOTYPE)
@Transaction
@Auditing
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool pool1;
    private final List<ConnectionPool> pools;
    @Value("${db.pool.size}")
    private final Integer size;

    @PostConstruct
    private void init() {
        log.warn("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method ...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method ...");
    }
}
