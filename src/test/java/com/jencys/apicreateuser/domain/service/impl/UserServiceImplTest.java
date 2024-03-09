package com.jencys.apicreateuser.domain.service.impl;

import com.jencys.apicreateuser.config.RegexProperties;
import com.jencys.apicreateuser.data.entity.User;
import com.jencys.apicreateuser.data.repository.PhoneRepository;
import com.jencys.apicreateuser.data.repository.UserRepository;
import com.jencys.apicreateuser.domain.service.UserService;
import com.jencys.apicreateuser.dto.PhoneDTO;
import com.jencys.apicreateuser.dto.UserRequestDTO;
import com.jencys.apicreateuser.dto.UserResponse;
import com.jencys.apicreateuser.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceImplTest {

    private UserRepository userRepository;
    private PhoneRepository phoneRepository;
    private RegexProperties regexProperties;
    private JwtTokenUtil jwtTokenUtil;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        phoneRepository = mock(PhoneRepository.class);
        regexProperties = mock(RegexProperties.class);
        jwtTokenUtil = mock(JwtTokenUtil.class);
        userService = new UserServiceImpl(userRepository, phoneRepository, regexProperties, jwtTokenUtil);
    }

    @Test
    void given_a_invalid_password_will_throw_error() {
        //arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO("jencys", "@@@", "any-password", Collections.emptyList());
        when(regexProperties.getPassword()).thenReturn("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$");

        //act & asserts
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(userRequestDTO));
    }

    @Test
    void given_a_invalid_email_will_throw_error() {
        //arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO("jencys", "@@@", "A@ny-1273a", Collections.emptyList());
        when(regexProperties.getPassword()).thenReturn("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$");
        when(regexProperties.getEmail()).thenReturn("any-regex");

        //act & asserts
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(userRequestDTO));
    }

    @Test
    void given_a_user_with_email_will_return_error_already_exists() {
        //arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO("jencys", "any-email@gmail.com", "A@ny-1273a", Collections.emptyList());
        when(regexProperties.getPassword()).thenReturn("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$");
        when(regexProperties.getEmail()).thenReturn("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(new User()));

        //act & asserts
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(userRequestDTO));
    }

    @Test
    void given_a_valid_user_will_return_ok() {
        User user = new User(UUID.randomUUID(), new Date(), new Date(), new Date(), "any-token", true, "any-name", "any-email@gmail.com", "any-password", Collections.emptyList());

        //arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO("jencys", "any-email@gmail.com", "A@ny-1273a", Collections.singletonList(new PhoneDTO("any-number", "any-city-code", "any-country-code")));
        when(regexProperties.getPassword()).thenReturn("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$");
        when(regexProperties.getEmail()).thenReturn("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(jwtTokenUtil.generateToken(anyString())).thenReturn("any-token");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(phoneRepository.saveAll(anyIterable())).thenReturn(Collections.emptyList());

        //act
        UserResponse response = userService.registerUser(userRequestDTO);

        //asserts

        assertNotNull(response.getId());
        assertNotNull(response.getCreated());
        assertNotNull(response.getModified());
        assertNotNull(response.getLastLogin());
        assertNotNull(response.getToken());
        assertNotNull(response.getIsActive());

        assertEquals("any-token", response.getToken());
        assertEquals(true, response.getIsActive());
    }
}