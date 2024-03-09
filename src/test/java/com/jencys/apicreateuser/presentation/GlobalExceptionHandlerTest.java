package com.jencys.apicreateuser.presentation;

import com.jencys.apicreateuser.dto.ErrorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void methodArgumentNotValidExceptionHandlerTest() {
        ResponseEntity<ErrorDTO> errorDTOResponseEntity = globalExceptionHandler.methodArgumentNotValidExceptionHandler(new IllegalArgumentException("any-message"));

        assertNotNull(errorDTOResponseEntity);
        assertNotNull(errorDTOResponseEntity.getBody());
        assertNotNull(errorDTOResponseEntity.getStatusCode());
        assertEquals(errorDTOResponseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}