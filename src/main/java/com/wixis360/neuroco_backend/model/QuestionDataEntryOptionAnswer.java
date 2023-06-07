package com.wixis360.neuroco_backend.model;

import lombok.Data;

import java.util.Date;

@Data
public class QuestionDataEntryOptionAnswer {
    private String id;
    private String questionId;
    private String questionDataEntryOptionId;
    private String answer;
    private String date;
    private String time;
    private Date createdTime;
}
