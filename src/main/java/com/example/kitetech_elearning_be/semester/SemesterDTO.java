package com.example.kitetech_elearning_be.semester;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
@Data
public class SemesterDTO {
    private Long id;

    private Long academicYearId;

    private String name;

    private String description;

    private SemesterStatus status;

    private LocalDate startDate;

    private LocalDate endDate;
}
