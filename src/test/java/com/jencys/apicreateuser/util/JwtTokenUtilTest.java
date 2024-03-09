package com.jencys.apicreateuser.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {
    JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil();
    }

    @Test
    void given_a_valid_email_will_return_token() {
        String token = jwtTokenUtil.generateToken("any-email@gmail.com");
        assertNotNull(token);
    }
}