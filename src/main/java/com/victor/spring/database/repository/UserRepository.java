package com.victor.spring.database.repository;

import com.victor.spring.database.entity.Role;
import com.victor.spring.database.entity.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
           "where u.firstname like %:firstname% and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(nativeQuery = true,
            value = "SELECT u.* FROM users u WHERE u.username = :username")
    List<User> findAllByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query("update User u " +
           "set u.role = :role " +
           "where  u.id in (:ids)")
    int updateRole(Role role, Long... ids);

        Optional<User> findTopByOrderByIdDesc();

        List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);

        @Query(value = "select u from User u",
        countQuery = "select count(distinct u.firstname) from User u")
        Page<User> findAllBy(Pageable pageable);
}
