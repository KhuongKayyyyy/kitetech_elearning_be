package com.example.kitetech_elearning_be.academic_year;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AcademicYearDTO {
    private Long id;
    private int year;
    private LocalDateTime endDate;
    private LocalDateTime startDate;
    private AcademicYearStatus status;
}
