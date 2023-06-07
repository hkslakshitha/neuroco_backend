package com.wixis360.neuroco_backend.service;

import com.wixis360.neuroco_backend.dto.QuestionDataEntryOptionAnswerDto;

public interface QuestionDataEntryOptionAnswerService {
    QuestionDataEntryOptionAnswerDto save(QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDto);
    QuestionDataEntryOptionAnswerDto findById(String id);
    QuestionDataEntryOptionAnswerDto update(QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDto);
    QuestionDataEntryOptionAnswerDto deleteById(String id);
}
