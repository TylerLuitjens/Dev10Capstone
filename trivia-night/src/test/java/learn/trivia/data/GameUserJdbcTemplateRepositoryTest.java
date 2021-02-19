package learn.trivia.data;

import learn.trivia.models.GameUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GameUserJdbcTemplateRepositoryTest {


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
        List<GameUser> users = repository.findByGameCode("ABCD");
        assertTrue(users.size() > 0);
   }

    @Test
    void shouldFindNoUsersForMissingGame() {
        List<GameUser> users = repository.findByGameCode("zzzz");
        assertEquals(users.size(), 0);
    }

    @Test
    void shouldAdd() {
        GameUser gameUser = new GameUser();
        gameUser.setUserId(2);
        gameUser.setGameCode("ABCD");

        assertTrue(repository.addGameUser(gameUser));
    }

    @Test
    void shouldAddByGame() {
        assertTrue(repository.addGameUser("ABCD", 3));
    }

    @Test
    void shouldUpdate() {
        GameUser gameUser = new GameUser();
        gameUser.setUserId(1);
        gameUser.setGameCode("ABCD");
        gameUser.setNumCorrect(5);
        gameUser.setNumAnswered(10);

        boolean success = repository.updateGameUser(gameUser);
        assertTrue(success);

        GameUser actual = repository.findGameUser("ABCD", 1);
        assertEquals(actual.getNumAnswered(), 10);
        assertEquals(actual.getNumCorrect(), 5);
    }
}
