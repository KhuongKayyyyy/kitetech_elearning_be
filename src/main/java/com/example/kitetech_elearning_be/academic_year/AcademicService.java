package com.example.kitetech_elearning_be.academic_year;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicService {
    private final AcademicYearRepository academicYearRepository;
    private final AcademicYearMapper academicYearMapper;

    public List<AcademicYearDTO> getAllAcademicYears() {
        return academicYearRepository.findAll().stream().map(
                ac -> academicYearMapper.toDTO(ac, new AcademicYearDTO())
        ).toList();
    }
}
