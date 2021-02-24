package learn.trivia.data;

import learn.trivia.data.mappers.GameUserMapper;
import learn.trivia.models.GameUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class GameUserJdbcTemplateRepository implements GameUserRepository{

    private JdbcTemplate template;

    public GameUserJdbcTemplateRepository (JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public GameUser findGameUser(String gameCode, int userId) {
        String sql = "SELECT * FROM game_user WHERE game_code = ? AND user_id = ?;";

        return template.query(sql, new GameUserMapper(), gameCode, userId).stream().findFirst().orElse(null);
    }

    @Override
    public List<GameUser> findByGameCode(String gameCode) {
        String sql = "SELECT * FROM game_user WHERE game_code = ? ORDER BY num_correct DESC;";

        return template.query(sql, new GameUserMapper(), gameCode);

    }

    @Override
    public List<GameUser> findByUser(int userId) {
        String sql = "SELECT * FROM game_user WHERE user_id = ? ORDER BY num_correct DESC;";

        return template.query(sql, new GameUserMapper(), userId);
    }

    @Override
    public boolean addGameUser(String gameCode, int userId) {
        String sql = "INSERT INTO game_user (game_code,user_id, num_answered, num_correct) " +
                "VALUES (?,?,0,0);";

        int rowsAffected = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gameCode);
            ps.setInt(2, userId);
            return ps;
        });

        return rowsAffected > 0;
    }

    @Override
    public boolean addGameUser(GameUser gameUser) {
        String sql = "INSERT INTO game_user (game_code,user_id, num_answered, num_correct) " +
                "VALUES (?,?,0,0);";

        int rowsAffected = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, gameUser.getGameCode());
            ps.setInt(2, gameUser.getUserId());
            return ps;
        });

        return rowsAffected > 0;
    }

    @Override
    public boolean updateGameUser(GameUser gameUser) {
        String sql = "UPDATE game_user SET " +
                "num_answered = ?, " +
                "num_correct = ? " +
                "WHERE user_id = ? AND game_code = ?;";

        int rowsAffected = template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, gameUser.getNumAnswered());
            ps.setInt(2, gameUser.getNumCorrect());
            ps.setInt(3, gameUser.getUserId());
            ps.setString(4, gameUser.getGameCode());

            return ps;
        });

        return rowsAffected > 0;
    }
}
