package com.victor.spring.database.repository;

import com.victor.spring.database.entity.Role;
import com.victor.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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
}
