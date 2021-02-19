package learn.trivia.data;

import learn.trivia.models.Answer;
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

   }

    @Test
    void shouldFindNoUsersForMissingGame() {

    }

    @Test
    void shouldAdd() {

    }

    @Test
    void shouldAddByGame() {

    }

    @Test
    void shouldUpdate() {

    }

    @Test
    void shouldNotUpdateName() {

    }

    @Test
    void shouldNotUpdateGameId() {

    }
}
