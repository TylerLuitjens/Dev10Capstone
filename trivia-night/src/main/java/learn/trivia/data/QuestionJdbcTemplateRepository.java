package learn.trivia.data;

import learn.trivia.data.mappers.QuestionMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

public class QuestionJdbcTemplateRepository implements QuestionRepository{

    private final JdbcTemplate jdbcTemplate;

    public QuestionJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Question> findByCategory(String category) {
        final String sql = "select q.question_id, q.question, q.category_id, c.category_name,  "
                + "from question q "
                + "inner join join category c on q.category_id = c.category_id "
                + "where c.category_name = ?;";

        return jdbcTemplate.query(sql, new QuestionMapper(), category);
    }

    @Override
    public Question addQuestion(Question question) {

        final String sql = "insert into question (question) "
                + "values (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, question.getQuestion());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        question.setQuestionId(keyHolder.getKey().intValue());
        return question;
    }

    private List<Question> selectQuestions(List<Question> questions) {
        return questions;
    }
}
