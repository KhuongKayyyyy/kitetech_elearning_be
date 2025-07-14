package com.example.kitetech_elearning_be.academic_year;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/academic_year")

public class AcademicYearController {

    private final AcademicService academicService;

    @GetMapping("/")
    public ResponseEntity<List<AcademicYearDTO>> getAcademicYears() {
        return ResponseEntity.ok(academicService.getAllAcademicYears());
    }
}
