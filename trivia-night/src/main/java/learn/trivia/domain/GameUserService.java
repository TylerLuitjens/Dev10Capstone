package learn.trivia.domain;

import learn.trivia.data.GameRepository;
import learn.trivia.data.UserRepository;
import learn.trivia.models.GameUser;
import learn.trivia.models.User;
import org.springframework.stereotype.Service;

@Service
public class GameUserService {

    private final UserRepository userRepository;
    private final GameUserRepository gameUserRespository;
    private final GameRepository gameRepository;

    public GameUserService(UserRepository userRepository, GameUserRepository gameUserRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameUserRespository = gameUserRepository;
        this.gameRepository = gameRepository;
    }

    public GameUser findGameUser(String gameCode, int userId) {
        return gameUserRepository.findGameUser(gameCode, userId);
    }

    public Result<GameUser> createGameUser(String gameCode, int userId) {
        Result<GameUser> result = validate(userId);

        if (gameCode.isBlank()) {
            result.addMessage("Game code is required.", ResultType.INVALID);
        }

        if (gameRepository.findByCode(gameCode) == null) {
            String message = String.format("Game code %s not found", gameCode);
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        if (userRepository.findById(userId) == null) {
            String message = String.format("User with id %s not found", userId);
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        GameUser gameUser = gameUserRepository.create(gameCode, userId);
        result.setPayload(gameUser);
        return result;
    }

    public Result<GameUser> updateGameUser(GameUser gameUser) {
        Result<GameUser> result = validate(gameUser.getUserId());

        if (gameUserRespository.findGameUser(gameUser.getGameCode(), gameUser.getUserId()) == null) {
            String message = "Game user not found";
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        GameUser gameUserUpdated = gameUserRepository.update(gameUser);
        result.setPayload(gameUserUpdated);
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
