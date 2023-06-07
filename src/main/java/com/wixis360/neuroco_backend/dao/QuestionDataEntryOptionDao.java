package com.wixis360.neuroco_backend.dao;

import com.wixis360.neuroco_backend.model.QuestionDataEntryOption;

import java.util.List;
import java.util.Optional;

public interface QuestionDataEntryOptionDao {
    int save(QuestionDataEntryOption questionDataEntryOption);
    List<QuestionDataEntryOption> findAllByQuestionId(String questionId);
    Optional<QuestionDataEntryOption> findById(String id);
}
