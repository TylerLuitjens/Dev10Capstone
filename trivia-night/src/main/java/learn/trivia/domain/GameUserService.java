package learn.trivia.domain;

import learn.trivia.data.GameRepository;
import learn.trivia.data.UserRepository;
import learn.trivia.models.GameUser;
import learn.trivia.data.GameUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameUserService {

    private final UserRepository userRepository;
    private final GameUserRepository gameUserRepository;
    private final GameRepository gameRepository;

    public GameUserService(UserRepository userRepository, GameUserRepository gameUserRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameUserRepository = gameUserRepository;
        this.gameRepository = gameRepository;
    }

    public GameUser findGameUser(String gameCode, int userId) {
        return gameUserRepository.findGameUser(gameCode, userId);
    }

    public List<GameUser> findByGameCode(String gameCode) {
        return gameUserRepository.findByGameCode(gameCode);
    }

    public Result<GameUser> createGameUser(String gameCode, int userId) {
        Result<GameUser> result = validate(userId);

        if (gameCode.isBlank()) {
            result.addMessage("Game code is required.", ResultType.INVALID);
        }

        if (gameRepository.findGameByCode(gameCode) == null) {
            String message = String.format("Game code %s not found", gameCode);
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        if (userRepository.findById(userId) == null) {
            String message = String.format("User with id %s not found", userId);
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        boolean success = gameUserRepository.addGameUser(gameCode, userId);

        if (!success) {
            result.addMessage("Unable to add user to game", ResultType.INVALID);
        }

        return result;
    }

    public Result<GameUser> updateGameUser(GameUser gameUser) {
        Result<GameUser> result = validate(gameUser.getUserId());

        if (gameUserRepository.findGameUser(gameUser.getGameCode(), gameUser.getUserId()) == null) {
            String message = "Game user not found";
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        boolean success = gameUserRepository.updateGameUser(gameUser);

        if (!success) {
            result.addMessage("Unable to upload data for this user", ResultType.NOT_FOUND);
        }

        return result;
    }


    private Result<GameUser> validate(int userId) {
        Result<GameUser> result = new Result<>();

        if (userId <= 0) {
            result.addMessage("User Id must be set for this operation.", ResultType.INVALID);
            return result;
        }
        return result;
    }
}
