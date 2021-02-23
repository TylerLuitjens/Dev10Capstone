package learn.trivia.data.mappers;

import learn.trivia.models.Game;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {
        Game game = new Game();
        game.setGameCode(resultSet.getString("game_code"));
        return game;
    }
}
