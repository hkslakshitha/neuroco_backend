package com.wixis360.neuroco_backend.dao;

import com.wixis360.neuroco_backend.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao {
    int save(Question question);
    List<Question> findAll();
    Optional<Question> findById(String id);
}
