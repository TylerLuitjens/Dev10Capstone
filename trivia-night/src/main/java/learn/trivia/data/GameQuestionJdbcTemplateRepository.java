package learn.trivia.data;

import learn.trivia.data.mappers.GameQuestionMapper;
import learn.trivia.models.GameQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class GameQuestionJdbcTemplateRepository implements GameQuestionRepository{
    private JdbcTemplate template;

    public GameQuestionJdbcTemplateRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public GameQuestion getGameQuestion(String gameCode, int questionId) {
        String sql = "SELECT * FROM game_question " +
                "INNER JOIN question ON game_question.question_id = question.question_id " +
                "WHERE game_code = ?;";

        return template.query(sql, new GameQuestionMapper(), gameCode, questionId).stream().findFirst().orElse(null);
    }

    @Override
    public List<GameQuestion> getGameQuestionsByGame(String gameCode) {
        String sql = "SELECT * FROM game_question " +
                "INNER JOIN question ON game_question.question_id = question.question_id " +
                "WHERE game_code = ?;";

        return template.query(sql, new GameQuestionMapper(), gameCode);
    }

    @Override
    public boolean addGameQuestion(String gameCode, int questionId) {
        String sql = "INSERT INTO game_question (game_code, question_id)" +
                "VALUES (?,?);";

        int rowsAffected = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gameCode);
            ps.setInt(2, questionId);
            return ps;
        });

        return rowsAffected > 0;
    }

    @Override
    public boolean addGameQuestion(GameQuestion gameQuestion) {
        String sql = "INSERT INTO game_question (game_code, question_id)" +
                "VALUES (?,?);";

        int rowsAffected = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gameQuestion.getGameCode());
            ps.setInt(2, gameQuestion.getQuestionId());
            return ps;
        });

        return rowsAffected > 0;
    }
}
