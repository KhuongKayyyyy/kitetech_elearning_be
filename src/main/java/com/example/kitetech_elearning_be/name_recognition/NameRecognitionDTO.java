package com.example.kitetech_elearning_be.name_recognition;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class NameRecognitionDTO {
    private Long id;
    private String studentID;
    private String classSessionID;
}
