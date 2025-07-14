package com.example.kitetech_elearning_be.academic_year;

import com.example.kitetech_elearning_be.respone.ApiResponse;
import com.example.kitetech_elearning_be.respone.Metadata;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/academic_year")
public class AcademicYearController {

    private final AcademicService academicService;

    @GetMapping("/listAll")
    public ResponseEntity<ApiResponse<List<AcademicYearDTO>>> getAllAsList() {
        List<AcademicYearDTO> academicYears = academicService.getAllAcademicYears(); // without pageable

        Metadata metadata = new Metadata(
                academicYears.size(),
                academicYears.size(),
                1,
                academicYears.size(),
                false
        );
        ApiResponse<List<AcademicYearDTO>> response = new ApiResponse<>(
                true,
                "Fetched academic years successfully",
                metadata,
                academicYears
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<List<AcademicYearDTO>>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        if (!List.of("id", "year", "startDate", "endDates", "status").contains(sort)) {
            throw new IllegalArgumentException("Invalid sort field: " + sort);
        }
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));

        Page<AcademicYearDTO> pagedResult = academicService.getAllAcademicYears(pageable);

        Metadata metadata = new Metadata(
                (int) pagedResult.getTotalElements(),
                pagedResult.getNumberOfElements(),
                pagedResult.getNumber() + 1,
                pagedResult.getSize(),
                pagedResult.hasNext()
        );

        ApiResponse<List<AcademicYearDTO>> response = new ApiResponse<>(
                true,
                "Fetched academic years with pagination",
                metadata,
                pagedResult.getContent()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<AcademicYearDTO>>> searchByYear(@RequestParam int year) {
        List<AcademicYearDTO> academicYears = academicService.findByYear(year); // without pageable

        Metadata metadata = new Metadata(
                academicYears.size(),
                academicYears.size(),
                1,
                academicYears.size(),
                false
        );
        ApiResponse<List<AcademicYearDTO>> response = new ApiResponse<>(
                true,
                "Fetched academic years successfully",
                metadata,
                academicYears
        );

        return ResponseEntity.ok(response);
    }
}
