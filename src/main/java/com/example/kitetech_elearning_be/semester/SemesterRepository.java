package com.example.kitetech_elearning_be.semester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SemesterRepository  extends JpaRepository<SemesterEntity, UUID> {
}
