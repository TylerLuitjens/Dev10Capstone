package learn.trivia.data;

import learn.trivia.models.Game;

import java.util.List;

public interface GameRepository {

    List<Game> findAll();

    Game findGameByCode(String gameCode);

    Game createGame(String gameCode);
}
