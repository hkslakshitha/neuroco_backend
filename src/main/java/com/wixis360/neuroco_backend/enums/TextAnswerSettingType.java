package com.wixis360.neuroco_backend.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TextAnswerSettingType {
    @JsonProperty("Max words")
    MAX_WORDS("Max words"),
    @JsonProperty("Max characters")
    MAX_CHARACTERS("Max characters"),
    @JsonProperty("Unlimited")
    UNLIMITED("Unlimited");

    private final String textAnswerSettingType;

    TextAnswerSettingType(String textAnswerSettingType) {
        this.textAnswerSettingType = textAnswerSettingType;
    }

    public String getTextAnswerSettingType() {
        return textAnswerSettingType;
    }

    public static TextAnswerSettingType fromValue(String value) {
        switch (value) {
            case "Max words":
                return MAX_WORDS;
            case "Max characters":
                return MAX_CHARACTERS;
            case "Unlimited":
                return UNLIMITED;
            default:
                return null;
        }
    }
}
