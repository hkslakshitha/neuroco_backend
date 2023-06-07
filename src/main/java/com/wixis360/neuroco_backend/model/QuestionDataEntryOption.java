package com.wixis360.neuroco_backend.model;

import com.wixis360.neuroco_backend.enums.CalendarSettingType;
import com.wixis360.neuroco_backend.enums.TextAnswerSettingType;
import lombok.Data;

@Data
public class QuestionDataEntryOption {
    private String id;
    private String questionId;
    private String name;
    private boolean hasTextInput;
    private String textInputTitle;
    private TextAnswerSettingType textAnswerSettingType;
    private String textAnswerSettingValue;
    private boolean hasCalendarInput;
    private CalendarSettingType calendarSettingType;
    private boolean hasTimeInput;
}
