package learn.trivia.data;

import learn.trivia.data.mappers.QuestionMapper;
import learn.trivia.models.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

@Repository
public class QuestionJdbcTemplateRepository implements QuestionRepository{

    private final JdbcTemplate jdbcTemplate;

    public QuestionJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Question> findByCategory(String category) {
        final String sql = "select question_id, question, category_name "
                + "from question "
                + "where category_name = ? "
                + "order by rand() "
                + "limit 10;";

        return jdbcTemplate.query(sql, new QuestionMapper(), category);
    }

    @Override
    public Question addQuestion(Question question) {

        final String sql = "insert into question (question, category_name) "
                + "values (?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, question.getQuestion());
            ps.setString(2, question.getCategory());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        question.setQuestionId(keyHolder.getKey().intValue());
        return question;
    }

}
