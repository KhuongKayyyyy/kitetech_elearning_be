package com.example.kitetech_elearning_be.name_recognition;

import com.example.kitetech_elearning_be.respone.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/name_recognition")
public class NameRecognitionController {
    private final NameRecognitionService nameRecognitionService;
    private final NameRecognitionMapper nameRecognitionMapper;
    @PostMapping
    public ResponseEntity<ApiResponse<NameRecognitionDTO>> create(@RequestBody NameRecognitionDTO nameRecognitionDTO) {
        NameRecognitionEntity savedEntity = nameRecognitionService.createNameRecognition(nameRecognitionDTO);
        NameRecognitionDTO responseDTO = nameRecognitionMapper.toDTO(savedEntity, new NameRecognitionDTO());

        ApiResponse<NameRecognitionDTO> response = new ApiResponse<>(
                true,
                "Name recognition record created successfully",
                null, // You can replace this with real Metadata if needed
                responseDTO
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<NameRecognitionDTO>>> getAll() {
        List<NameRecognitionDTO> nameRecognitionDTOList = nameRecognitionService.getNameRecognitions();
        ApiResponse<List<NameRecognitionDTO>> response = new ApiResponse<>(
                true,
                "Fetched academic years successfully",
                null,
                nameRecognitionDTOList
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-student-id")
    public ResponseEntity<ApiResponse<List<NameRecognitionDTO>>> getByStudentID(
            @RequestParam(required = false) String studentID
    ) {
        List<NameRecognitionDTO> nameRecognitionDTOList = nameRecognitionService.getNameRecognitionsByStudentId(studentID);
        ApiResponse<List<NameRecognitionDTO>> response = new ApiResponse<>(
                true,
                "Fetched name recognitions by student ID successfully",
                null,
                nameRecognitionDTOList
        );
        return ResponseEntity.ok(response);
    }



    @GetMapping("/by-class-session-id")
    public ResponseEntity<ApiResponse<List<NameRecognitionDTO>>> getByClassSessionID(
            @RequestParam(required = false) String classSessionID
    ) {
        List<NameRecognitionDTO> nameRecognitionDTOList = nameRecognitionService.getNameRecognitionsByClassSessionID(classSessionID);
        ApiResponse<List<NameRecognitionDTO>> response = new ApiResponse<>(
                true,
                "Fetched name recognitions by class session ID successfully",
                null,
                nameRecognitionDTOList
        );
        return ResponseEntity.ok(response);
    }
}
