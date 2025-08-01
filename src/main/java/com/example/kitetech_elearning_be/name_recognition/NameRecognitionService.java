package com.example.kitetech_elearning_be.name_recognition;

import com.example.kitetech_elearning_be.exception.DuplicateNameRecognitionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NameRecognitionService {
    private final NameRecognitionMapper nameRecognitionMapper;
    private final NameRecognitionRepository nameRecognitionRepository;

    public List<NameRecognitionDTO> getNameRecognitions() {
        return nameRecognitionRepository.findAll().stream().map(
                nr -> nameRecognitionMapper.toDTO(nr, new NameRecognitionDTO())
        ).toList();
    }

    public List<NameRecognitionDTO> getNameRecognitionsByStudentId(String studentId) {
        return nameRecognitionRepository.findByStudentID(studentId)
                .stream()
                .map(nr -> nameRecognitionMapper.toDTO(nr, new NameRecognitionDTO()))
                .toList();
    }

    public List<NameRecognitionDTO> getNameRecognitionsByClassSessionID(String classSessionID) {
        return nameRecognitionRepository.findByClassSessionID(classSessionID)
                .stream()
                .map(nr -> nameRecognitionMapper.toDTO(nr, new NameRecognitionDTO()))
                .toList();
    }


    public NameRecognitionEntity createNameRecognition(NameRecognitionDTO nameRecognitionDTO) {
        System.out.println(nameRecognitionDTO);
        boolean exists = nameRecognitionRepository.existsByClassSessionIDAndStudentID(
                nameRecognitionDTO.getClassSessionID(),
                nameRecognitionDTO.getStudentID()
        );

        if (exists) {
            throw new DuplicateNameRecognitionException("Student already registered for this class session.");
        }

        final NameRecognitionEntity nameRecognitionEntity = NameRecognitionEntity
                .builder()
                .name(nameRecognitionDTO.getName())
                .studentID(nameRecognitionDTO.getStudentID())
                .classSessionID(nameRecognitionDTO.getClassSessionID())
                .time(LocalDateTime.now())
                .build();

        return nameRecognitionRepository.save(nameRecognitionEntity);
    }

}
