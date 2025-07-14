package com.example.kitetech_elearning_be.academic_year;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "academic_years")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicYearEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int year;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDates;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcademicYearStatus status = AcademicYearStatus.Active;
}
