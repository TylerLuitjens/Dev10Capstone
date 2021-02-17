//package learn.trivia.data;
//
//import learn.trivia.data.mappers.AnswerMapper;
//import learn.trivia.models.Answer;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//
//import java.sql.PreparedStatement;
//import java.sql.Statement;
//import java.util.List;
//
//public class AnswerJdbcTemplateRepository implements AnswerRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public AnswerJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<Answer> findByQuestionId(int questionId) {
//        final String sql = "select answer "
//                + "from question "
//                + "where question_id = ?;";
//
//        return jdbcTemplate.query(sql, new AnswerMapper());
//    }
//
//    @Override
//    public Answer findByAnswerId(int answerId) {
//        final String sql = "select answer "
//                + "from answer "
//                + "where answer_id = ?;";
//
//        Answer answer = jdbcTemplate.query(sql, new AnswerMapper(), answerId);
//
//        return answer;
//    }
//
//    @Override
//    public Answer addAnswer(Answer answer) {
//        return null;
//    }
//}
