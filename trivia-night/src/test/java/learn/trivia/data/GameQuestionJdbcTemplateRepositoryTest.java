package learn.trivia.data;

import learn.trivia.models.Game;
import learn.trivia.models.GameQuestion;
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
    GameQuestionRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindQuestionsByGame() {
        String gameCode = "ABCD";
        List<GameQuestion> users = repository.findByGameCode(gameCode);
        assertTrue(users.size() > 0);
    }

    @Test
    void shouldFindNoQuestionsForMissingGame() {
        String gameCode = "zzzz";
        List<GameQuestion> users = repository.findByGameCode(gameCode);

        assertEquals(users.size(), 0);
    }

    @Test
    void shouldAdd() {
        String gameCode = "ABCD";
        GameQuestion gameQuestion = new GameQuestion();
        gameQuestion.setGameCode(gameCode);
        gameQuestion.setQuestionId(3);

        boolean success = repository.addGameQuestion(gameQuestion);
        assertTrue(success);
        assertTrue(repository.findByGameCode(gameCode).size() > 1);
    }

    @Test
    void shouldAddByData() {
        String gameCode = "ABCD";
        int questionId = 4;

        boolean success = repository.addGameQuestion(gameCode, questionId);
        assertTrue(success);
        assertTrue(repository.findByGameCode(gameCode).size() > 1);
    }

}
