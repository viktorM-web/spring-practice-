package com.victor.spring.integration.service;

import com.victor.spring.database.entity.Role;
import com.victor.spring.dto.UserCreateEditDto;
import com.victor.spring.dto.UserReadDto;
import com.victor.spring.integration.IntegrationTestBase;
import com.victor.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;

    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();

        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);

        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }

    @Test
    void create() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );
        UserReadDto actualResult = userService.create(userCreateEditDto);

        assertEquals(userCreateEditDto.getUsername(), actualResult.getUsername());
        assertEquals(userCreateEditDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userCreateEditDto.getLastname(), actualResult.getLastname());
        assertEquals(userCreateEditDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userCreateEditDto.getCompanyId(), actualResult.getCompany().id());
        assertSame(userCreateEditDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto(
                "test@gmail.com",
                "test",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_1,
                new MockMultipartFile("test", new byte[0])
        );
        var actualResult = userService.update(USER_1, userCreateEditDto);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals("test@gmail.com", user.getUsername());
            assertEquals(userCreateEditDto.getUsername(), user.getUsername());
            assertEquals(userCreateEditDto.getFirstname(), user.getFirstname());
            assertEquals(userCreateEditDto.getLastname(), user.getLastname());
            assertEquals(userCreateEditDto.getBirthDate(), user.getBirthDate());
            assertEquals(userCreateEditDto.getCompanyId(), user.getCompany().id());
            assertSame(userCreateEditDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
       assertTrue(userService.delete(USER_1));
       assertFalse(userService.delete(-124L));
    }
}
