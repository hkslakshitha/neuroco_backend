package com.wixis360.neuroco_backend.controller;

import com.wixis360.neuroco_backend.dto.APIResponse;
import com.wixis360.neuroco_backend.dto.QuestionDataEntryOptionAnswerDto;
import com.wixis360.neuroco_backend.enums.APIResponseStatus;
import com.wixis360.neuroco_backend.service.QuestionDataEntryOptionAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/question_data_entry_option_answer")
@AllArgsConstructor
public class QuestionDataEntryOptionAnswerController {
    private final QuestionDataEntryOptionAnswerService questionDataEntryOptionAnswerService;

    @PostMapping
    public ResponseEntity<APIResponse> save(@RequestBody QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDto) {
        QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerResponse = questionDataEntryOptionAnswerService.save(questionDataEntryOptionAnswerDto);

        if (questionDataEntryOptionAnswerResponse == null) {
            APIResponse<QuestionDataEntryOptionAnswerDto> responseDTO = APIResponse
                    .<QuestionDataEntryOptionAnswerDto>builder()
                    .status(APIResponseStatus.ERROR.name())
                    .results(null)
                    .build();
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }

        APIResponse<QuestionDataEntryOptionAnswerDto> responseDTO = APIResponse
                .<QuestionDataEntryOptionAnswerDto>builder()
                .status(APIResponseStatus.SUCCESS.name())
                .results(questionDataEntryOptionAnswerResponse)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findById(@PathVariable String id) {
        QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDtoResponse = questionDataEntryOptionAnswerService.findById(id);
        APIResponse<QuestionDataEntryOptionAnswerDto> responseDTO = APIResponse
                .<QuestionDataEntryOptionAnswerDto>builder()
                .status(APIResponseStatus.SUCCESS.name())
                .results(questionDataEntryOptionAnswerDtoResponse)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable String id) {
        QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDtoResponse = questionDataEntryOptionAnswerService.deleteById(id);
        APIResponse<QuestionDataEntryOptionAnswerDto> responseDTO = APIResponse
                .<QuestionDataEntryOptionAnswerDto>builder()
                .status(APIResponseStatus.SUCCESS.name())
                .results(questionDataEntryOptionAnswerDtoResponse)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> update(@RequestBody QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDto) {
        QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerResponse = questionDataEntryOptionAnswerService.update(questionDataEntryOptionAnswerDto);

        if (questionDataEntryOptionAnswerResponse == null) {
            APIResponse<QuestionDataEntryOptionAnswerDto> responseDTO = APIResponse
                    .<QuestionDataEntryOptionAnswerDto>builder()
                    .status(APIResponseStatus.ERROR.name())
                    .results(null)
                    .build();
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }

        APIResponse<QuestionDataEntryOptionAnswerDto> responseDTO = APIResponse
                .<QuestionDataEntryOptionAnswerDto>builder()
                .status(APIResponseStatus.SUCCESS.name())
                .results(questionDataEntryOptionAnswerResponse)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
