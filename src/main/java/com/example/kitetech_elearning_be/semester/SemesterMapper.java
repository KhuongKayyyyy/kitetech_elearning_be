package com.example.kitetech_elearning_be.semester;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SemesterMapper {
    private final SemesterRepository semesterRepository;

    public SemesterDTO toDTO(final SemesterEntity academicYearEntity, final SemesterDTO academicYearDTO) {
        academicYearDTO.setId(academicYearEntity.getId());
        academicYearDTO.setName(academicYearEntity.getName());
        academicYearDTO.setDescription(academicYearEntity.getDescription());
        academicYearDTO.setAcademicYearId(academicYearEntity.getAcademicYear() != null ? academicYearEntity.getAcademicYear().getId() : null);
        academicYearDTO.setStatus(academicYearEntity.getStatus());
        academicYearDTO.setStartDate(academicYearEntity.getStartDate());
        academicYearDTO.setEndDate(academicYearEntity.getEndDate());
        return academicYearDTO;
    }

    public SemesterEntity toEntity(final SemesterDTO academicYearDTO, final SemesterEntity academicYearEntity) {
        academicYearEntity.setId(academicYearDTO.getId());
        academicYearEntity.setName(academicYearDTO.getName());
        academicYearEntity.setDescription(academicYearDTO.getDescription());
        academicYearEntity.setStatus(academicYearDTO.getStatus());
        academicYearEntity.setStartDate(academicYearDTO.getStartDate());
        academicYearEntity.setEndDate(academicYearDTO.getEndDate());
        return academicYearEntity;
    }
}


