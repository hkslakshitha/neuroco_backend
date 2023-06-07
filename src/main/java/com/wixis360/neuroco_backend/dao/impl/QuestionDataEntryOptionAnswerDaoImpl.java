package com.wixis360.neuroco_backend.dao.impl;

import com.wixis360.neuroco_backend.dao.QuestionDataEntryOptionAnswerDao;
import com.wixis360.neuroco_backend.model.QuestionDataEntryOptionAnswer;
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
public class QuestionDataEntryOptionAnswerDaoImpl implements QuestionDataEntryOptionAnswerDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDatasource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int save(QuestionDataEntryOptionAnswer questionDataEntryOptionAnswer) {
        String sql = "INSERT INTO QUESTION_DATA_ENTRY_OPTION_ANSWER (ID,QUESTION_ID,QUESTION_DATA_ENTRY_OPTION_ID,ANSWER," +
                "DATE,TIME,CREATED_TIME) VALUES (:id,:questionId,:questionDataEntryOptionId,:answer,:date,:time,:createdTime)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", questionDataEntryOptionAnswer.getId());
        mapSqlParameterSource.addValue("questionId", questionDataEntryOptionAnswer.getQuestionId());
        mapSqlParameterSource.addValue("questionDataEntryOptionId", questionDataEntryOptionAnswer.getQuestionDataEntryOptionId());
        mapSqlParameterSource.addValue("answer", questionDataEntryOptionAnswer.getAnswer());
        mapSqlParameterSource.addValue("date", questionDataEntryOptionAnswer.getDate());
        mapSqlParameterSource.addValue("time", questionDataEntryOptionAnswer.getTime());
        mapSqlParameterSource.addValue("createdTime", questionDataEntryOptionAnswer.getCreatedTime());
        return namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public List<QuestionDataEntryOptionAnswer> findAllByQuestionIdAndQuestionDataEntryOptionId(String questionId, String questionDataEntryOptionId) {
        String sql = "SELECT * FROM QUESTION_DATA_ENTRY_OPTION_ANSWER WHERE QUESTION_ID=:questionId AND QUESTION_DATA_ENTRY_OPTION_ID=:questionDataEntryOptionId ORDER BY CREATED_TIME DESC";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("questionId", questionId);
        mapSqlParameterSource.addValue("questionDataEntryOptionId", questionDataEntryOptionId);
        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, (rs, rowNum) -> getQuestionDataEntryOptionAnswer(rs));
    }

    @Override
    public Optional<QuestionDataEntryOptionAnswer> findById(String id) {
        String sql = "SELECT * FROM QUESTION_DATA_ENTRY_OPTION_ANSWER WHERE ID=:id";
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), (rs, rowNum) -> getQuestionDataEntryOptionAnswer(rs)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int update(QuestionDataEntryOptionAnswer questionDataEntryOptionAnswer) {
        String sql = "UPDATE QUESTION_DATA_ENTRY_OPTION_ANSWER SET ANSWER=:answer,DATE=:date,TIME=:time WHERE ID=:id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", questionDataEntryOptionAnswer.getId());
        mapSqlParameterSource.addValue("answer", questionDataEntryOptionAnswer.getAnswer());
        mapSqlParameterSource.addValue("date", questionDataEntryOptionAnswer.getDate());
        mapSqlParameterSource.addValue("time", questionDataEntryOptionAnswer.getTime());
        return namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public int deleteById(String id) {
        String sql = "DELETE FROM QUESTION_DATA_ENTRY_OPTION_ANSWER WHERE ID=:id";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        return namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    private QuestionDataEntryOptionAnswer getQuestionDataEntryOptionAnswer(ResultSet rs) throws SQLException {
        QuestionDataEntryOptionAnswer questionDataEntryOptionAnswer = new QuestionDataEntryOptionAnswer();
        questionDataEntryOptionAnswer.setId(rs.getString("ID"));
        questionDataEntryOptionAnswer.setQuestionId(rs.getString("QUESTION_ID"));
        questionDataEntryOptionAnswer.setQuestionDataEntryOptionId(rs.getString("QUESTION_DATA_ENTRY_OPTION_ID"));
        questionDataEntryOptionAnswer.setAnswer(rs.getString("ANSWER"));
        questionDataEntryOptionAnswer.setDate(rs.getString("DATE"));
        questionDataEntryOptionAnswer.setTime(rs.getString("TIME"));
        questionDataEntryOptionAnswer.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
        return questionDataEntryOptionAnswer;
    }
}
