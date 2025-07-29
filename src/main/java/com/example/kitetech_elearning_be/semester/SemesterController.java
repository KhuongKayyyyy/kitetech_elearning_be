package com.example.kitetech_elearning_be.semester;

import com.example.kitetech_elearning_be.academic_year.AcademicYearDTO;
import com.example.kitetech_elearning_be.respone.ApiResponse;
import com.example.kitetech_elearning_be.respone.Metadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/semester")
public class SemesterController {
    private final SemesterService semesterService;

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<SemesterDTO>>> getAllAsList() {
        List<SemesterDTO> semesters = semesterService.getAllSemesters();

        ApiResponse<List<SemesterDTO>> response = new ApiResponse<>(
                true,
                "Fetched semester successfully",
                null,
                semesters
        );
        return ResponseEntity.ok(response);
    }
}
