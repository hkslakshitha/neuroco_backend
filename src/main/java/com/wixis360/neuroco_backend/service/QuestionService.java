package com.wixis360.neuroco_backend.service;

import com.wixis360.neuroco_backend.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto save(QuestionDto questionDto);
    List<QuestionDto> findAll();
    QuestionDto findById(String id);
}
