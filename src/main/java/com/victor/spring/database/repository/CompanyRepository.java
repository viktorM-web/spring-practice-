package com.victor.spring.database.repository;

import com.victor.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    //optional, Entity, Future
    @Query("select c from Company c " +
           "join fetch c.locales cl " +
           "where c.name = :name2")
    Optional<Company> findByName(@Param("name2") String name);

    //Collection, Stream(batch, close)
    List<Company> findAllByNameContainingIgnoreCase(String fragment);
}
