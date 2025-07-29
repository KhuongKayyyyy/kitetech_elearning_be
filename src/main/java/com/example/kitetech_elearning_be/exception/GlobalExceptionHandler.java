package com.example.kitetech_elearning_be.exception;

import com.example.kitetech_elearning_be.respone.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateNameRecognitionException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicateException(DuplicateNameRecognitionException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                false,
                ex.getMessage(),
                null,
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // returns 400
    }

}
