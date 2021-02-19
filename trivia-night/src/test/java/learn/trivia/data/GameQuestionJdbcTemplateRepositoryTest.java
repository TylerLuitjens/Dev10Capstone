package learn.trivia.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void shouldFindQuestionsByGame() {

    }

    @Test
    void shouldFindNoUsersForMissingGame() {

    }

    @Test
    void shouldAdd() {

    }

    @Test
    void shouldAddByData() {

    }

}
