package learn.trivia.data;

import learn.trivia.models.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameJdbcTemplateRepositoryTest {

    @Autowired
    GameJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void shouldFindAllGames() {
        List<Game> games = repository.findAll();
        assertNotNull(games);
        assertEquals(10, games.size());
    }

    @Test
    void shouldFindGame() {
        Game game = repository.findGameByCode("AAAA");
        assertNotNull(game);
    }

    @Test
    void shouldCreateGame() {
        Game game = makeGame();
        Game actual = repository.createGame(game);
        assertNotNull(actual);
    }

    private Game makeGame() {
        Game game = new Game();
        game.setGameCode("ABCD");
        return game;
    }
}