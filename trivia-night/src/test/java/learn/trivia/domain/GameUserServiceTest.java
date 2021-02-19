package learn.trivia.domain;

import learn.trivia.data.GameRepository;
import learn.trivia.data.UserRepository;
import learn.trivia.models.GameUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GameUserServiceTest {

    @Autowired
    GameUserService service;

    @MockBean
    UserRepository userRepository;

    @MockBean
    GameUserRepository gameUserRepository;

    @MockBean
    GameRepository gameRepository;

    @Test
    void shouldFindGameUser() {
        GameUser gameUser = makeGameUser();

        when(gameUserRepository.findGameUser("WXyZ", 1)).thenReturn(gameUser);

        GameUser actual = service.findGameUser("WXyZ", 1);
        assertEquals(gameUser, actual);
    }

    @Test
    void shouldFindNotFindGameUserWithInvalidGameCodeOrUserId() {
        when(gameUserRepository.findGameUser("ZZZZ", 1000)).thenReturn(null);

        GameUser actual = service.findGameUser("ZZZZ", 1000);
        assertNull(actual);
    }

    @Test
    void shouldCreateGameUser() {
        GameUser gameUser = makeGameUser();

        when(gameUserRepository.addGameUser("WXyz", 1)).thenReturn(gameUser);

        Result<GameUser> actual = service.createGameUser("WXyz", 1);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(gameUser, actual.getPayload());
    }

    // should not create GameUser with blank game code
    @Test
    void shouldNotCreateGameUserWithBlankCode() {

        Result<GameUser> actual = service.createGameUser("", 1);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    // should not create GameUser with invalid game code
    @Test
    void shouldNotCreateGameUserWithInvalidCode() {
        when(gameRepository.findGameByCode("ZZZZ")).thenReturn(null);

        Result<GameUser> actual = service.createGameUser("ZZZZ", 1);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    // should not create GameUser with invalid userId
    @Test
    void shouldNotCreateGameUserWithInvalidUserId() {
        when(userRepository.findById(1000)).thenReturn(null);

        Result<GameUser> actual = service.createGameUser("ZZZZ", 1000);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    // should update game user
    @Test
    void shouldUpdateGameUser() {
        GameUser gameUser = makeGameUser();

        when(gameUserRepository.update(gameUser)).thenReturn(gameUser);

        Result<GameUser> actual = service.updateGameUser(gameUser);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(gameUser, actual.getPayload());
    }

    // should not update game user not matching records
    @Test
    void shouldNotUpdateNonExistingGameUser() {
        GameUser gameUser = makeGameUser();

        when(gameUserRepository.findGameUser(gameUser)).thenReturn(null);

        Result<GameUser> actual = service.updateGameUser(gameUser);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    GameUser makeGameUser() {
        GameUser gameUser = new GameUser();

        gameUser.setGameCode("WXyZ");
        gameUser.setNumAnswered(2);
        gameUser.setNumCorrect(1);
        gameUser.setUserId(1);

        return gameUser;
    }

}
