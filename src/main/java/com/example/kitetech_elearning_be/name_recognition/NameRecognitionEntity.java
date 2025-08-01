package com.example.kitetech_elearning_be.name_recognition;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "name_recognition")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameRecognitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String studentID;
    @Column(nullable = false)
    private String classSessionID;

    @Column(nullable = false)
    private LocalDateTime time;
}
