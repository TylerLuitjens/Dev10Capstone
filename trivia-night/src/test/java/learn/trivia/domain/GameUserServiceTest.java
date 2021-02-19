package learn.trivia.domain;

import learn.trivia.data.GameRepository;
import learn.trivia.data.UserRepository;
import learn.trivia.models.Game;
import learn.trivia.models.GameUser;
import learn.trivia.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import learn.trivia.data.GameUserRepository;
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

        when(gameRepository.findGameByCode("WXyZ")).thenReturn(makeGame());
        when(userRepository.findById(1)).thenReturn(makeUser());
        when(gameUserRepository.addGameUser("WXyZ", 1)).thenReturn(true);

        Result<GameUser> actual = service.createGameUser("WXyZ", 1);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    // should not create GameUser with blank game code
    @Test
    void shouldNotCreateGameUserWithBlankCode() {

        Result<GameUser> actual = service.createGameUser("", 1);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
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

        when(gameUserRepository.findGameUser(gameUser.getGameCode(), gameUser.getUserId())).thenReturn(gameUser);
        when(gameUserRepository.updateGameUser(gameUser)).thenReturn(true);

        Result<GameUser> actual = service.updateGameUser(gameUser);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    // should not update game user not matching records
    @Test
    void shouldNotUpdateNonExistingGameUser() {
        GameUser gameUser = makeGameUser();

        when(gameUserRepository.findGameUser(gameUser.getGameCode(), gameUser.getUserId())).thenReturn(null);

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

    Game makeGame() {

        Game game = new Game();

        game.setGameCode("WXyZ");

        return game;
    }

    User makeUser() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("Test");
        user.setPassword("Pass");
        user.setNumAnswered(2);
        user.setNumCorrect(1);

        return user;
    }
}
