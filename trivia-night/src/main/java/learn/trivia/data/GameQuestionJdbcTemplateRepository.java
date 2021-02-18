package learn.trivia.data;

import learn.trivia.models.GameQuestion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameQuestionJdbcTemplateRepository implements GameQuestionRepository{
    private JdbcTemplate template;

    public GameQuestionJdbcTemplateRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public GameQuestion getGameQuestion(String gameCode, int questionId) {
        String sql = "SELECT * FROM game_question WHERE game_code = ? AND question_id = ?;";
        return null;
    }

    @Override
    public List<GameQuestion> getGameQuestionsByGame(String gameCode) {
        String sql = "SELECT * FROM game_question WHERE game_code = ?;";
        return null;
    }

    @Override
    public boolean addGameQuestion(String gameCode, int questionId) {
        String sql = "INSERT INTO game_question (game_code, question_id)" +
                "VALUES (?,?);";
        return false;
    }

    @Override
    public boolean addGameQuestion(GameQuestion gameQuestion) {
        String sql = "INSERT INTO game_question (game_code, question_id)" +
                "VALUES (?,?);";
        return false;
    }
}
