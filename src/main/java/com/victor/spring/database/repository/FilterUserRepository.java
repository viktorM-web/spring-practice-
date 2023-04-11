package com.victor.spring.database.repository;

import com.victor.spring.database.entity.User;
import com.victor.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}
