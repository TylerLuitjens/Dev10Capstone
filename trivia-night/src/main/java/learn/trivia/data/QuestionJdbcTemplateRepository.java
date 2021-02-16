package learn.trivia.data;

import learn.trivia.data.mappers.QuestionMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;

public class QuestionJdbcTemplateRepository implements QuestionRepository{

    private final JdbcTemplate jdbcTemplate;

    public QuestionJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Question> findByCategory(int categoryId) {
        final String sql = "select question_id, question "
                + "from question "
                + "where category_id = ?;";

        return jdbcTemplate.query(sql, new QuestionMapper());
    }

    private List<Question> selectQuestions(List<Question> questions) {
        return questions;
    }
}
