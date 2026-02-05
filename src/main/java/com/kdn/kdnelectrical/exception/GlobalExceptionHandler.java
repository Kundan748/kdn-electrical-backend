package com.kdn.kdnelectrical.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kdn.kdnelectrical.dto.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔴 Handle RuntimeException (your custom throws)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleRuntime(RuntimeException ex) {

        ApiErrorResponse error =
                new ApiErrorResponse(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 🔴 Handle NullPointerException
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiErrorResponse> handleNullPointer() {

        ApiErrorResponse error =
                new ApiErrorResponse("Something went wrong. Please contact support.");

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 🔴 Handle ALL unknown errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneral(Exception ex) {

        ApiErrorResponse error =
                new ApiErrorResponse("Unexpected server error");

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
