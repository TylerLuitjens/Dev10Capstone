package learn.trivia.data.mappers;

import learn.trivia.models.GameUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameUserMapper implements RowMapper<GameUser> {
    @Override
    public GameUser mapRow(ResultSet resultSet, int i) throws SQLException {
        GameUser gameUser = new GameUser();
        gameUser.setUserId(resultSet.getInt("user_id"));
        gameUser.setGameCode(resultSet.getString("game_code"));
        gameUser.setNumAnswered(resultSet.getInt("num_answered"));
        gameUser.setNumCorrect(resultSet.getInt("num_correct"));
        return gameUser;
    }
}
