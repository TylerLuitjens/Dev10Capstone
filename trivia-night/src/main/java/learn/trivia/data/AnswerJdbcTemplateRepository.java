package learn.trivia.data;

import learn.trivia.data.mappers.AnswerMapper;
import learn.trivia.models.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AnswerJdbcTemplateRepository implements AnswerRepository {

    private final JdbcTemplate jdbcTemplate;

    public AnswerJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> findByQuestionId(int questionId) {
        final String sql = "select answer_id "
                + "from answer "
                + "where question_id = ?;";

        return jdbcTemplate.query(sql, new AnswerMapper(), questionId);
    }

    @Override
    public Answer findByAnswerId(int answerId) {
        final String sql = "select answer_id "
                + "from answer "
                + "where answer_id = ?;";

        return jdbcTemplate.query(sql, new AnswerMapper(), answerId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Answer addAnswer(Answer answer) {
        return null;
    }
}
