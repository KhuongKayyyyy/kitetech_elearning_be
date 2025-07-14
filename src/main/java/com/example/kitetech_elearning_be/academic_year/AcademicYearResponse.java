package com.example.kitetech_elearning_be.academic_year;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class AcademicYearResponse {
    private int total;
    private List<AcademicYearDTO> academicYears;
}
