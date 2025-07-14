package com.example.kitetech_elearning_be.academic_year;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AcademicYearMapper {
    private final AcademicYearRepository academicYearRepository;

    public AcademicYearDTO toDTO(final AcademicYearEntity academicYearEntity, final AcademicYearDTO academicYearDTO) {
        academicYearDTO.setId(academicYearEntity.getId());
        academicYearDTO.setYear(academicYearEntity.getYear());
        academicYearDTO.setStartDate(academicYearEntity.getStartDate());
        academicYearDTO.setEndDate(academicYearEntity.getEndDates());
        academicYearDTO.setStatus(academicYearEntity.getStatus());
        return academicYearDTO;
    }

    public AcademicYearEntity toEntity(final AcademicYearDTO academicYearDTO, final AcademicYearEntity academicYearEntity) {
        academicYearEntity.setYear(academicYearDTO.getYear());
        academicYearEntity.setStartDate(academicYearDTO.getStartDate());
        academicYearEntity.setEndDates(academicYearDTO.getEndDate());
        academicYearEntity.setStatus(academicYearDTO.getStatus());
        return academicYearEntity;
    }
}
