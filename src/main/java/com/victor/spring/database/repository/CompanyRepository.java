package com.victor.spring.database.repository;

import com.victor.spring.bpp.Auditing;
import com.victor.spring.bpp.InjectBean;
import com.victor.spring.bpp.Transaction;
import com.victor.spring.database.entity.Company;
import com.victor.spring.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

//    @Resource(name="poll1")

//    @Autowired
//    @Qualifier("pool1")
    private ConnectionPool pool1;
    @Autowired
    private List<ConnectionPool> pools;
    @Value("${db.pool.size}")
    private Integer size;

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
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

    @Autowired
    public void setPool1(ConnectionPool pool1) {
        this.pool1 = pool1;
    }
}
