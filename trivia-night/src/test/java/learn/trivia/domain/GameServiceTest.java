package learn.trivia.domain;

import learn.trivia.data.GameRepository;
import learn.trivia.models.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GameServiceTest {

    @Autowired
    GameService service;

    @MockBean
    GameRepository gameRepository;

    // should find all games
    @Test
    void shouldFindAll() {
        Game firstGame = makeGame();
        Game secondGame = makeGame();
        secondGame.setGameCode("AbcD");

        List<Game> expected = List.of(firstGame, secondGame);

        when(gameRepository.findAll()).thenReturn(expected);

        List<Game> actual = service.findAllGames();
        assertEquals(expected, actual);
    }

    // should find game by code
    @Test
    void shouldFindByCode() {
        Game firstGame = makeGame();

        when(gameRepository.findGameByCode("WXyZ")).thenReturn(firstGame);

        Game actual = service.findGameByCode("WXyZ");
        assertEquals(firstGame, actual);

    }

    // should not find game by nonmatching code
    @Test
    void shouldFindByNonExistingCode() {
        when(gameRepository.findGameByCode("ALPH")).thenReturn(null);

        Game actual = service.findGameByCode("null");
        assertNull(actual);

    }

    // should create game

    Game makeGame() {

    Game game = new Game();

    game.setGameCode("WXyZ");

    return game;
    }



}
