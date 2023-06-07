package com.wixis360.neuroco_backend.model;

import lombok.Data;

import java.util.Date;

@Data
public class Question {
    private String id;
    private String question;
    private int numberOfOption;
    private boolean isCompulsory;
    private Date createdTime;
}
