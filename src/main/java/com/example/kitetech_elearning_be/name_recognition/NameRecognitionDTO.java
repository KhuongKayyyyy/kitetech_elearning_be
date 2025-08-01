package com.example.kitetech_elearning_be.name_recognition;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NameRecognitionDTO {
    private Long id;
    private String name;
    private String studentID;
    private String classSessionID;
    private LocalDateTime time;
}
