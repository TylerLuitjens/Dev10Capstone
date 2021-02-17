package learn.trivia.data;

import learn.trivia.data.mappers.GameMapper;
import learn.trivia.models.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameJdbcTemplateRepository implements GameRepository {

    private final JdbcTemplate jdbcTemplate;

    public GameJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Game> findAll() {
        final String sql = "select game_code "
                + "from game;";

        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game findGameByCode(String gameCode) {
        final String sql = "select game_code "
                + "from game "
                + "where game_code = ?;";

        return jdbcTemplate.query(sql, new GameMapper(), gameCode).stream()
                .findFirst().orElse(null);
    }

    @Override
    public Game createGame(Game game) {
        return null;
    }
}
