package com.example.kitetech_elearning_be.academic_year;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYearEntity, Long> {
}
