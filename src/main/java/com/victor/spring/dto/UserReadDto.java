package com.victor.spring.dto;

import com.victor.spring.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserReadDto {
    Long id;
    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    String image;
    Role role;
    CompanyReadDto company;
}
