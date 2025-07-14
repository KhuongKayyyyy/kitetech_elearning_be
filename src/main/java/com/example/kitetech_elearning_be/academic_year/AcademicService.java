package com.example.kitetech_elearning_be.academic_year;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public Page<AcademicYearDTO> getAllAcademicYears(Pageable pageable) {

        return academicYearRepository.findAll(pageable)
                .map(ac -> academicYearMapper.toDTO(ac, new AcademicYearDTO()));
    }

    public List<AcademicYearDTO> findByYear(int year) {
        try {
            return academicYearRepository.findAllByYear(year).stream()
                    .map(ac -> academicYearMapper.toDTO(ac, new AcademicYearDTO()))
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }



}
