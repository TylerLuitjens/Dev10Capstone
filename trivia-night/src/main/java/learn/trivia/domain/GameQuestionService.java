package learn.trivia.domain;

import learn.trivia.data.GameQuestionRepository;
import learn.trivia.data.GameRepository;
import learn.trivia.data.QuestionRepository;
import learn.trivia.data.UserRepository;
import learn.trivia.models.GameQuestion;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.List;

@Service
public class GameQuestionService {
    private final GameQuestionRepository gameQuestionRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public GameQuestionService(GameQuestionRepository gameQuestionRepository, UserRepository userRepository,
                               GameRepository gameRepository) {
        this.gameQuestionRepository = gameQuestionRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public List<GameQuestion> findByGameCode(String gameCode) {
        return gameQuestionRepository.findByGameCode(gameCode);
    }

    public Result<GameQuestion> createGameQuestion(String gameCode, int userId) {
        Result<GameQuestion> result = new Result<>();

        if (gameRepository.findGameByCode(gameCode) == null) {
            String message = String.format("Game code %s not found", gameCode);
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        if (userRepository.findById(userId) == null) {
            String message = String.format("User %s not found", userId);
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        boolean success = gameQuestionRepository.addGameQuestion(gameCode, userId);

        if (!success) {
            result.addMessage("Database error. Unable to add User to Game", ResultType.INVALID);
        }

        return result;
    }

}