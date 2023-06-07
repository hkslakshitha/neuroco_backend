package com.wixis360.neuroco_backend.controller;

import com.wixis360.neuroco_backend.dto.APIResponse;
import com.wixis360.neuroco_backend.dto.QuestionDto;
import com.wixis360.neuroco_backend.enums.APIResponseStatus;
import com.wixis360.neuroco_backend.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/question")
@AllArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<APIResponse> save(@RequestBody QuestionDto questionDto) {
        QuestionDto questionDtoResponse = questionService.save(questionDto);

        if (questionDtoResponse == null) {
            APIResponse<QuestionDto> responseDTO = APIResponse
                    .<QuestionDto>builder()
                    .status(APIResponseStatus.ERROR.name())
                    .results(null)
                    .build();
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }

        APIResponse<QuestionDto> responseDTO = APIResponse
                .<QuestionDto>builder()
                .status(APIResponseStatus.SUCCESS.name())
                .results(questionDtoResponse)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse> findAll() {
        APIResponse<List<QuestionDto>> responseDTO = APIResponse
                .<List<QuestionDto>>builder()
                .status(APIResponseStatus.SUCCESS.name())
                .results(questionService.findAll())
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable String id) {
        APIResponse<QuestionDto> responseDTO = APIResponse
                .<QuestionDto>builder()
                .status(APIResponseStatus.SUCCESS.name())
                .results(questionService.findById(id))
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
