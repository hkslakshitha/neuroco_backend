package com.wixis360.neuroco_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuestionDto {
    private String id;
    private String question;
    private int numberOfOption;
    @JsonProperty("isCompulsory")
    private boolean isCompulsory;
    private Date createdTime;
    private List<QuestionDataEntryOptionDto> questionDataEntryOptions;
}
