package com.example.kitetech_elearning_be.respone;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private Metadata metadata;
    private T data;
}