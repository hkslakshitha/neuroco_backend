package com.wixis360.neuroco_backend.dao;

import com.wixis360.neuroco_backend.model.QuestionDataEntryOptionAnswer;

import java.util.List;
import java.util.Optional;

public interface QuestionDataEntryOptionAnswerDao {
    int save(QuestionDataEntryOptionAnswer questionDataEntryOptionAnswer);
    List<QuestionDataEntryOptionAnswer> findAllByQuestionIdAndQuestionDataEntryOptionId(String questionId, String questionDataEntryOptionId);
    Optional<QuestionDataEntryOptionAnswer> findById(String id);
    int update(QuestionDataEntryOptionAnswer questionDataEntryOptionAnswer);
    int deleteById(String id);
}
