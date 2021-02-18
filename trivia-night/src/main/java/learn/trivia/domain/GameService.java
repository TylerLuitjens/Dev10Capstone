package learn.trivia.domain;

import learn.trivia.data.AnswerRepository;
import learn.trivia.data.GameRepository;
import learn.trivia.data.QuestionRepository;
import learn.trivia.data.UserRepository;
import learn.trivia.models.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService (GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public Game findGameByCode(String gameCode) {
        return gameRepository.findGameByCode(gameCode);
    }

    public Result<Game> create() {
        Result<Game> result = new Result<>();
        String gameCode = gameCodeGenerator();
        boolean isValid = false;

        while (!isValid) {
            int loopCount = 0;

            for (Game game : findAllGames()) {
                if (game.getGameCode().equals(gameCode)) {
                    gameCode = gameCodeGenerator();
                    loopCount = 0;
                } else {
                    loopCount++;
                }
            } if (loopCount == findAllGames().size()) {
                isValid = true;
            }
        }

        Game game = gameRepository.createGame(gameCode);
        result.setPayload(game);
        return result;
    }


    private String gameCodeGenerator() {
        Random randomNumber = new Random();
        String gameCode = "";

        for (int i = 0; i < 4; i++) {
            char selectedChar = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ"
                    .toCharArray()[randomNumber.nextInt("aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ"
                    .toCharArray().length)];
            gameCode = gameCode + selectedChar;
        }
        return gameCode;
    }

}
