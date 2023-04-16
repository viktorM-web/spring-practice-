package com.victor.spring.database.repository;

import com.victor.spring.database.entity.Role;
import com.victor.spring.database.entity.User;
import com.victor.spring.dto.PersonalInfo;
import com.victor.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);
    void updateCompanyAndRoleNamed(List<User> users);
}
