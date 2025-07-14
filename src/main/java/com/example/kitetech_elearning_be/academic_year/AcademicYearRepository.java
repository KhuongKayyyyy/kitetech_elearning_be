package com.example.kitetech_elearning_be.academic_year;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.UUID;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYearEntity, Long> {

    List<AcademicYearEntity> findAllByYear(int year);
}
