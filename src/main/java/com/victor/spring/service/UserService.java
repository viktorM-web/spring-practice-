package com.victor.spring.service;

import com.victor.spring.database.entity.Company;
import com.victor.spring.database.repository.CompanyRepository;
import com.victor.spring.database.repository.CrudRepository;
import com.victor.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;

}
