package com.wixis360.neuroco_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionDataEntryOptionAnswerDto {
    private String id;
    private String questionId;
    private String questionDataEntryOptionId;
    private String answer;
    private String date;
    private String time;
    private Date createdTime;
    private QuestionDataEntryOptionDto questionDataEntryOption;
}
