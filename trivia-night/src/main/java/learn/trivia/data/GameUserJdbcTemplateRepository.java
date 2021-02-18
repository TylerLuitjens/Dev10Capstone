package learn.trivia.data;

import learn.trivia.models.GameUser;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GameUserJdbcTemplateRepository implements GameUserRepository{

    private JdbcTemplate template;

    public GameUserJdbcTemplateRepository (JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public GameUser getGameUser(String gameCode, int userId) {
        String sql = "SELECT * FROM game_user WHERE game_code = ? AND user_id = ?;";
        return null;
    }

    @Override
    public List<GameUser> getGameUsersByGame(String gameCode) {
        String sql = "SELECT * FROM game_user WHERE game_code = ?;";

        return null;
    }

    @Override
    public boolean addGameUser(String gameCode, int userId) {
        String sql = "INSERT INTO game_user (game_code,user_id, num_answered, num_correct) " +
                "VALUES (?,?,0,0);";
        return false;
    }

    @Override
    public boolean addGameUser(GameUser gameUser) {
        String sql = "INSERT INTO game_user (game_code,user_id, num_answered, num_correct) " +
                "VALUES (?,?,0,0);";
        return false;
    }

    @Override
    public boolean updateGameUser(GameUser gameUser) {
        String sql = "UPDATE game_user SET" +
                "num_answered = ?," +
                "num_correct = ?" +
                "WHERE user_id = ? AND game_code = ?";
        return false;
    }
}
