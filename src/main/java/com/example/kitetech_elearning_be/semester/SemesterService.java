package com.example.kitetech_elearning_be.semester;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class SemesterService {
    private final SemesterRepository semesterRepository;
    private final SemesterMapper semesterMapper;

    public List<SemesterDTO> getAllSemesters() {
        return semesterRepository.findAll().stream().map(
                sem -> semesterMapper.toDTO(sem, new SemesterDTO())
        ).toList();
    }
}
