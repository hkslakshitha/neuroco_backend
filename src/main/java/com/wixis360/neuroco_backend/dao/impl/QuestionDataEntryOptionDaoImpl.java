package com.wixis360.neuroco_backend.dao.impl;

import com.wixis360.neuroco_backend.dao.QuestionDataEntryOptionDao;
import com.wixis360.neuroco_backend.enums.CalendarSettingType;
import com.wixis360.neuroco_backend.enums.TextAnswerSettingType;
import com.wixis360.neuroco_backend.model.QuestionDataEntryOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDataEntryOptionDaoImpl implements QuestionDataEntryOptionDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDatasource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int save(QuestionDataEntryOption questionDataEntryOption) {
        String sql = "INSERT INTO QUESTION_DATA_ENTRY_OPTION (ID,QUESTION_ID,NAME,HAS_TEXT_INPUT,TEXT_INPUT_TITLE," +
                "TEXT_ANSWER_SETTING_TYPE,TEXT_ANSWER_SETTING_VALUE,HAS_CALENDAR_INPUT,CALENDAR_SETTING_TYPE," +
                "HAS_TIME_INPUT) VALUES (:id,:questionId,:name,:hasTextInput,:textInputTitle,:textAnswerSettingType," +
                ":textAnswerSettingValue,:hasCalendarInput,:calendarSettingType,:hasTimeInput)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", questionDataEntryOption.getId());
        mapSqlParameterSource.addValue("questionId", questionDataEntryOption.getQuestionId());
        mapSqlParameterSource.addValue("name", questionDataEntryOption.getName());
        mapSqlParameterSource.addValue("hasTextInput", questionDataEntryOption.isHasTextInput());
        mapSqlParameterSource.addValue("textInputTitle", questionDataEntryOption.getTextInputTitle());
        mapSqlParameterSource.addValue("textAnswerSettingType", questionDataEntryOption.getTextAnswerSettingType() == null ? null : questionDataEntryOption.getTextAnswerSettingType().getTextAnswerSettingType());
        mapSqlParameterSource.addValue("textAnswerSettingValue", questionDataEntryOption.getTextAnswerSettingValue());
        mapSqlParameterSource.addValue("hasCalendarInput", questionDataEntryOption.isHasCalendarInput());
        mapSqlParameterSource.addValue("calendarSettingType", questionDataEntryOption.getCalendarSettingType() == null ? null : questionDataEntryOption.getCalendarSettingType().getCalendarSettingType());
        mapSqlParameterSource.addValue("hasTimeInput", questionDataEntryOption.isHasTimeInput());
        return namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public List<QuestionDataEntryOption> findAllByQuestionId(String questionId) {
        String sql = "SELECT * FROM QUESTION_DATA_ENTRY_OPTION WHERE QUESTION_ID = :questionId";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("questionId", questionId);
        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, (rs, rowNum) -> getQuestionDataEntryOption(rs));
    }

    @Override
    public Optional<QuestionDataEntryOption> findById(String id) {
        String sql = "SELECT * FROM QUESTION_DATA_ENTRY_OPTION WHERE ID = :id";
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), (rs, rowNum) -> getQuestionDataEntryOption(rs)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private QuestionDataEntryOption getQuestionDataEntryOption(ResultSet rs) throws SQLException {
        QuestionDataEntryOption questionDataEntryOption = new QuestionDataEntryOption();
        questionDataEntryOption.setId(rs.getString("ID"));
        questionDataEntryOption.setQuestionId(rs.getString("QUESTION_ID"));
        questionDataEntryOption.setName(rs.getString("NAME"));
        questionDataEntryOption.setHasTextInput(rs.getBoolean("HAS_TEXT_INPUT"));
        questionDataEntryOption.setTextInputTitle(rs.getString("TEXT_INPUT_TITLE"));
        questionDataEntryOption.setTextAnswerSettingType(TextAnswerSettingType.fromValue(rs.getString("TEXT_ANSWER_SETTING_TYPE")));
        questionDataEntryOption.setTextAnswerSettingValue(rs.getString("TEXT_ANSWER_SETTING_VALUE"));
        questionDataEntryOption.setHasCalendarInput(rs.getBoolean("HAS_CALENDAR_INPUT"));
        questionDataEntryOption.setCalendarSettingType(CalendarSettingType.fromValue(rs.getString("CALENDAR_SETTING_TYPE")));
        questionDataEntryOption.setHasTimeInput(rs.getBoolean("HAS_TIME_INPUT"));
        return questionDataEntryOption;
    }
}
