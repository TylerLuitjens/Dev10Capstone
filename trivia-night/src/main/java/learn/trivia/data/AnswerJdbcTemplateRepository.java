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
        final String sql = "select answer_id, question_id, answer, isCorrect "
                + "from answer "
                + "where question_id = ? "
                + "order by rand();";

        return jdbcTemplate.query(sql, new AnswerMapper(), questionId);
    }

    @Override
    public Answer findByAnswerId(int answerId) {
        final String sql = "select answer_id, question_id, answer, isCorrect "
                + "from answer "
                + "where answer_id = ?;";

        return jdbcTemplate.query(sql, new AnswerMapper(), answerId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Answer findByAnswer(String answer) {
        final String sql = "select answer_id, question_id, answer, isCorrect "
                + "from answer "
                + "where answer = ?;";

        return jdbcTemplate.query(sql, new AnswerMapper(), answer).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Answer addAnswer(Answer answer) {
        final String sql = "insert into answer (answer, question_id, isCorrect) "
                + "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, answer.getAnswer());
            ps.setInt(2, answer.getQuestionId());
            if (answer.isCorrect()) {
                ps.setInt(3, 1);
            } else {
                ps.setInt(3, 0);
            }
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        answer.setAnswerId(keyHolder.getKey().intValue());
        return answer;
    }
}
