package com.victor.spring.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.victor.spring.database.entity.Role;
import com.victor.spring.database.entity.User;
import com.victor.spring.database.querydsl.QPredicates;
import com.victor.spring.dto.PersonalInfo;
import com.victor.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.victor.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT 
                firstname,
                lastname,
                birth_date
            FROM users
            WHERE company_id = ?
                AND role = ?
            """;

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE,
                (rs, rowNum) -> new PersonalInfo(
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("birth_date").toLocalDate()),
                companyId, role.name());
    }
}
