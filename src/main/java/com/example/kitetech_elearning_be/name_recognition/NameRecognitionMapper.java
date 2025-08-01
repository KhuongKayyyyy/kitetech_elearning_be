package com.example.kitetech_elearning_be.name_recognition;

import com.example.kitetech_elearning_be.academic_year.AcademicYearEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NameRecognitionMapper {
    public NameRecognitionDTO toDTO(final NameRecognitionEntity nameRecognitionEntity, final NameRecognitionDTO nameRecognitionDTO) {
        nameRecognitionDTO.setId(nameRecognitionEntity.getId());
        nameRecognitionDTO.setName(nameRecognitionEntity.getName());
        nameRecognitionDTO.setStudentID(nameRecognitionEntity.getStudentID());
        nameRecognitionDTO.setClassSessionID(nameRecognitionEntity.getClassSessionID());
        nameRecognitionDTO.setTime(nameRecognitionEntity.getTime());
        return nameRecognitionDTO;
    }

    public NameRecognitionEntity toEntity(final NameRecognitionDTO nameRecognitionDTO, final NameRecognitionEntity nameRecognitionEntity) {
        nameRecognitionEntity.setId(nameRecognitionDTO.getId());
        nameRecognitionEntity.setName(nameRecognitionDTO.getName());
        nameRecognitionEntity.setStudentID(nameRecognitionDTO.getStudentID());
        nameRecognitionEntity.setClassSessionID(nameRecognitionDTO.getClassSessionID());
        nameRecognitionEntity.setTime(nameRecognitionDTO.getTime());
        return nameRecognitionEntity;
    }
}
