package com.wixis360.neuroco_backend.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CalendarSettingType {
    @JsonProperty("Year only")
    YEAR_ONLY("Year only"),
    @JsonProperty("Month only")
    MONTH_ONLY("Month only"),
    @JsonProperty("Month and Year only")
    MONTH_AND_YEAR_ONLY("Month and Year only"),
    @JsonProperty("Date, Month and Year only")
    DATE_MONTH_AND_YEAR_ONLY("Date, Month and Year only");

    private final String calendarSettingType;

    CalendarSettingType(String calendarSettingType) {
        this.calendarSettingType = calendarSettingType;
    }

    public String getCalendarSettingType() {
        return calendarSettingType;
    }

    public static CalendarSettingType fromValue(String value) {
        switch (value) {
            case "Year only":
                return YEAR_ONLY;
            case "Month only":
                return MONTH_ONLY;
            case "Month and Year only":
                return MONTH_AND_YEAR_ONLY;
            case "Date, Month and Year only":
                return DATE_MONTH_AND_YEAR_ONLY;
            default:
                return null;
        }
    }
}
