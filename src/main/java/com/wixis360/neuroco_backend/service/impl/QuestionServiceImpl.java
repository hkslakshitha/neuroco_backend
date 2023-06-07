package com.wixis360.neuroco_backend.service.impl;

import com.wixis360.neuroco_backend.dao.QuestionDao;
import com.wixis360.neuroco_backend.dao.QuestionDataEntryOptionAnswerDao;
import com.wixis360.neuroco_backend.dao.QuestionDataEntryOptionDao;
import com.wixis360.neuroco_backend.dto.QuestionDataEntryOptionAnswerDto;
import com.wixis360.neuroco_backend.dto.QuestionDataEntryOptionDto;
import com.wixis360.neuroco_backend.dto.QuestionDto;
import com.wixis360.neuroco_backend.exception.NotFoundException;
import com.wixis360.neuroco_backend.model.Question;
import com.wixis360.neuroco_backend.model.QuestionDataEntryOption;
import com.wixis360.neuroco_backend.model.QuestionDataEntryOptionAnswer;
import com.wixis360.neuroco_backend.service.QuestionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;
    private final QuestionDataEntryOptionDao questionDataEntryOptionDao;
    private final QuestionDataEntryOptionAnswerDao questionDataEntryOptionAnswerDao;
    private final ModelMapper mapper;

    @Override
    public QuestionDto save(QuestionDto questionDto) {
        questionDto.setId(UUID.randomUUID().toString());
        questionDto.setCreatedTime(new Date());

        if (questionDao.save(getQuestion(questionDto)) > 0) {
            List<QuestionDataEntryOptionDto> questionDataEntryOptions = questionDto.getQuestionDataEntryOptions();
            for (QuestionDataEntryOptionDto questionDataEntryOptionDto : questionDataEntryOptions) {
                questionDataEntryOptionDto.setId(UUID.randomUUID().toString());
                questionDataEntryOptionDto.setQuestionId(questionDto.getId());

                if (questionDataEntryOptionDto.isHasTextInput() == false) {
                    questionDataEntryOptionDto.setTextInputTitle(null);
                    questionDataEntryOptionDto.setTextAnswerSettingType(null);
                    questionDataEntryOptionDto.setTextAnswerSettingValue(null);
                }

                if (questionDataEntryOptionDto.isHasCalendarInput() == false) {
                    questionDataEntryOptionDto.setCalendarSettingType(null);
                }

                if (questionDataEntryOptionDao.save(getQuestionDataEntryOption(questionDataEntryOptionDto)) > 0) {
                    continue;
                } else {
                    return null;
                }
            }
            return questionDto;
        } else {
            return null;
        }
    }

    @Override
    public List<QuestionDto> findAll() {
        return questionDao.findAll().stream().map(this::getQuestionDto).toList();
    }

    @Override
    public QuestionDto findById(String id) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            QuestionDto questionDto = getQuestionDto(question);
            List<QuestionDataEntryOption> questionDataEntryOptions = questionDataEntryOptionDao.findAllByQuestionId(question.getId());
            List<QuestionDataEntryOptionDto> questionDataEntryOptionDtoList = questionDataEntryOptions.stream().map(this::getQuestionDataEntryOptionDto).toList();

            for (QuestionDataEntryOptionDto questionDataEntryOptionDto : questionDataEntryOptionDtoList) {
                List<QuestionDataEntryOptionAnswer> questionDataEntryOptionAnswers = questionDataEntryOptionAnswerDao.findAllByQuestionIdAndQuestionDataEntryOptionId(questionDataEntryOptionDto.getQuestionId(), questionDataEntryOptionDto.getId());
                List<QuestionDataEntryOptionAnswerDto> questionDataEntryOptionAnswerDtoList = questionDataEntryOptionAnswers.stream().map(this::getQuestionDataEntryOptionDto).toList();
                questionDataEntryOptionDto.setQuestionDataEntryOptionAnswers(questionDataEntryOptionAnswerDtoList);
            }

            questionDto.setQuestionDataEntryOptions(questionDataEntryOptionDtoList);
            return questionDto;
        } else {
            throw new NotFoundException("Question not found with id " + id);
        }
    }

    private Question getQuestion(QuestionDto questionDto){
        return mapper.map(questionDto, Question.class);
    }

    private QuestionDto getQuestionDto(Question question){
        return mapper.map(question, QuestionDto.class);
    }

    private QuestionDataEntryOption getQuestionDataEntryOption(QuestionDataEntryOptionDto questionDataEntryOptionDto){
        return mapper.map(questionDataEntryOptionDto, QuestionDataEntryOption.class);
    }

    private QuestionDataEntryOptionDto getQuestionDataEntryOptionDto(QuestionDataEntryOption questionDataEntryOption){
        return mapper.map(questionDataEntryOption, QuestionDataEntryOptionDto.class);
    }

    private QuestionDataEntryOptionAnswerDto getQuestionDataEntryOptionDto(QuestionDataEntryOptionAnswer questionDataEntryOptionAnswer){
        return mapper.map(questionDataEntryOptionAnswer, QuestionDataEntryOptionAnswerDto.class);
    }
}
