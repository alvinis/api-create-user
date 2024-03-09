package com.jencys.apicreateuser.presentation;

import com.jencys.apicreateuser.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body(new ErrorDTO(String.format("Error intentando persistir al usuario: %s", Arrays.toString(ex.getDetailMessageArguments()))));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidExceptionHandler(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body(new ErrorDTO(ex.getMessage()));
    }
}
