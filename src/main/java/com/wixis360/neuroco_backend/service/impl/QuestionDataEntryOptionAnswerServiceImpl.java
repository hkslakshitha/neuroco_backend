package com.wixis360.neuroco_backend.service.impl;

import com.wixis360.neuroco_backend.dao.QuestionDataEntryOptionAnswerDao;
import com.wixis360.neuroco_backend.dao.QuestionDataEntryOptionDao;
import com.wixis360.neuroco_backend.dto.QuestionDataEntryOptionAnswerDto;
import com.wixis360.neuroco_backend.dto.QuestionDataEntryOptionDto;
import com.wixis360.neuroco_backend.exception.NotFoundException;
import com.wixis360.neuroco_backend.model.QuestionDataEntryOption;
import com.wixis360.neuroco_backend.model.QuestionDataEntryOptionAnswer;
import com.wixis360.neuroco_backend.service.QuestionDataEntryOptionAnswerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class QuestionDataEntryOptionAnswerServiceImpl implements QuestionDataEntryOptionAnswerService {
    private final QuestionDataEntryOptionDao questionDataEntryOptionDao;
    private final QuestionDataEntryOptionAnswerDao questionDataEntryOptionAnswerDao;
    private final ModelMapper mapper;

    @Override
    public QuestionDataEntryOptionAnswerDto save(QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDto) {
        questionDataEntryOptionAnswerDto.setId(UUID.randomUUID().toString());
        questionDataEntryOptionAnswerDto.setCreatedTime(new Date());
        return questionDataEntryOptionAnswerDao.save(getQuestionDataEntryOptionAnswer(questionDataEntryOptionAnswerDto)) > 0 ? questionDataEntryOptionAnswerDto : null;
    }

    @Override
    public QuestionDataEntryOptionAnswerDto findById(String id) {
        Optional<QuestionDataEntryOptionAnswer> optionalQuestionDataEntryOptionAnswer = questionDataEntryOptionAnswerDao.findById(id);

        if (optionalQuestionDataEntryOptionAnswer.isPresent()) {
            QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDto = getQuestionDataEntryOptionAnswerDto(optionalQuestionDataEntryOptionAnswer.get());
            Optional<QuestionDataEntryOption> optionalQuestionDataEntryOption = questionDataEntryOptionDao.findById(questionDataEntryOptionAnswerDto.getQuestionDataEntryOptionId());

            if (optionalQuestionDataEntryOption.isPresent()) {
                questionDataEntryOptionAnswerDto.setQuestionDataEntryOption(getQuestionDataEntryOptionDto(optionalQuestionDataEntryOption.get()));
            }

            return questionDataEntryOptionAnswerDto;
        }

        throw new NotFoundException("Question data entry option answer not found with id : " + id);
    }

    @Override
    public QuestionDataEntryOptionAnswerDto update(QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDto) {
        return questionDataEntryOptionAnswerDao.update(getQuestionDataEntryOptionAnswer(questionDataEntryOptionAnswerDto)) > 0 ? questionDataEntryOptionAnswerDto : null;
    }

    @Override
    public QuestionDataEntryOptionAnswerDto deleteById(String id) {
        Optional<QuestionDataEntryOptionAnswer> optionalQuestionDataEntryOptionAnswer = questionDataEntryOptionAnswerDao.findById(id);

        if (optionalQuestionDataEntryOptionAnswer.isPresent()) {
            return questionDataEntryOptionAnswerDao.deleteById(id) > 0 ? getQuestionDataEntryOptionAnswerDto(optionalQuestionDataEntryOptionAnswer.get()) : null;
        }

        throw new NotFoundException("Question data entry option answer not found with id : " + id);
    }

    private QuestionDataEntryOptionDto getQuestionDataEntryOptionDto(QuestionDataEntryOption questionDataEntryOption) {
        return mapper.map(questionDataEntryOption, QuestionDataEntryOptionDto.class);
    }

    private QuestionDataEntryOptionAnswer getQuestionDataEntryOptionAnswer(QuestionDataEntryOptionAnswerDto questionDataEntryOptionAnswerDto) {
        return mapper.map(questionDataEntryOptionAnswerDto, QuestionDataEntryOptionAnswer.class);
    }

    private QuestionDataEntryOptionAnswerDto getQuestionDataEntryOptionAnswerDto(QuestionDataEntryOptionAnswer questionDataEntryOptionAnswer) {
        return mapper.map(questionDataEntryOptionAnswer, QuestionDataEntryOptionAnswerDto.class);
    }
}
