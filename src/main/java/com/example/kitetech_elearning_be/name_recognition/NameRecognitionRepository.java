package com.example.kitetech_elearning_be.name_recognition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NameRecognitionRepository extends JpaRepository<NameRecognitionEntity, Long> {
    boolean existsByClassSessionIDAndStudentID(String classSessionID, String studentID);

    List<NameRecognitionEntity> findByStudentID(String studentID);

    List<NameRecognitionEntity> findByClassSessionID(String classSessionID);
}
