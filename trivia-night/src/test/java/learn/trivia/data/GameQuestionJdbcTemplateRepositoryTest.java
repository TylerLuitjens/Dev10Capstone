package learn.trivia.data;

import learn.trivia.models.GameUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GameQuestionJdbcTemplateRepositoryTest {

    @Autowired
    GameUserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindUsersByGame() {
        String gameCode = "ABCD";
        List<GameUser> users = repository.findByGameCode(gameCode);
        assertTrue(users.size() > 0);
    }

    @Test
    void shouldFindNoUsersForMissingGame() {
        String gameCode = "zzzz";
        List<GameUser> users = repository.findByGameCode(gameCode);

        assertEquals(users.size(), 0);
    }

    @Test
    void shouldAdd() {
        String gameCode = "ABCD";
        GameUser gameUser = new GameUser();
        gameUser.setGameCode(gameCode);
        gameUser.setUserId(2);
        gameUser.setNumAnswered(0);
        gameUser.setNumCorrect(0);

        boolean success = repository.addGameUser(gameUser);
        assertTrue(success);
        assertTrue(repository.findByGameCode(gameCode).size() > 1);
    }

    @Test
    void shouldAddByData() {
        String gameCode = "ABCD";

        boolean success = repository.addGameUser(gameCode, 3);
        assertTrue(success);
        assertTrue(repository.findByGameCode(gameCode).size() > 1);
    }

}
