package com.example.kitetech_elearning_be.semester;

import com.example.kitetech_elearning_be.academic_year.AcademicYearEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "semester")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SemesterEntity {
    @Id
    @GeneratedValue(generator = "UUID",strategy = GenerationType.AUTO)
    private UUID id;



    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYearEntity academicYear;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SemesterStatus status = SemesterStatus.ACTIVE;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;
}


//Table Semesters {
//id integer [pk, increment]
//academic_year_id integer [ref: > Academic_Years.id] // Liên kết với niên học
//name varchar(50) [not null, unique] // Tên học kỳ, ví dụ: "Fall 2023", "Spring 2024"
//start_date date [not null] // Ngày bắt đầu học kỳ
//end_date date [not null] // Ngày kết thúc học kỳ
//status enum('Active', 'Closed', 'ExamPeriod') [default: 'Active'] // Trạng thái học kỳ
//description text // Mô tả thêm (tùy chọn)
//}