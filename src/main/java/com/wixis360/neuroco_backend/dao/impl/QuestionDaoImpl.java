package com.wixis360.neuroco_backend.dao.impl;

import com.wixis360.neuroco_backend.dao.QuestionDao;
import com.wixis360.neuroco_backend.model.Question;
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
public class QuestionDaoImpl implements QuestionDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDatasource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int save(Question question) {
        String sql = "INSERT INTO QUESTION (ID,QUESTION,NUMBER_OF_OPTION,IS_COMPULSORY,CREATED_TIME) VALUES " +
                "(:id,:question,:numberOfOption,:isCompulsory,:createdTime)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", question.getId());
        mapSqlParameterSource.addValue("question", question.getQuestion());
        mapSqlParameterSource.addValue("numberOfOption", question.getNumberOfOption());
        mapSqlParameterSource.addValue("isCompulsory", question.isCompulsory());
        mapSqlParameterSource.addValue("createdTime", question.getCreatedTime());
        return namedParameterJdbcTemplate.update(sql, mapSqlParameterSource);
    }

    @Override
    public List<Question> findAll() {
        String sql = "SELECT * FROM QUESTION ORDER BY CREATED_TIME DESC";
        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> getQuestion(rs));
    }

    @Override
    public Optional<Question> findById(String id) {
        String sql = "SELECT * FROM QUESTION WHERE ID=:id";
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), (rs, rowNum) -> getQuestion(rs)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Question getQuestion(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setId(rs.getString("ID"));
        question.setQuestion(rs.getString("QUESTION"));
        question.setNumberOfOption(rs.getInt("NUMBER_OF_OPTION"));
        question.setCompulsory(rs.getBoolean("IS_COMPULSORY"));
        question.setCreatedTime(rs.getTimestamp("CREATED_TIME"));
        return question;
    }
}
