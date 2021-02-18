package learn.trivia.domain;

import learn.trivia.data.GameQuestionRepository;
import learn.trivia.data.GameRepository;
import learn.trivia.data.QuestionRepository;
import learn.trivia.models.GameQuestion;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameQuestionService {
    private final GameQuestionRepository gameQuestionRepository;
    private final QuestionRepository questionRepository;
    private final GameRepository gameRepository;

    public GameQuestionService(GameQuestionRepository gameQuestionRepository, QuestionRepository questionRepository,
                               GameRepository gameRepository) {
        this.gameQuestionRepository = gameQuestionRepository;
        this.questionRepository = questionRepository;
        this.gameRepository = gameRepository;
    }

    public List<GameQuestion> findByGameCode(String gameCode) {
        return gameQuestionRepository.findByGameCode(gameCode);
    }

    public Result<GameQuestion> createGameQuestion(String gameCode, String category) {
        Result<GameQuestion> result = new Result<>();

        if (gameRepository.findByGameCode == null) {
            String message = String.format("Game code %s not found", gameCode);
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        if (questionRepository.findByCategory(category).size() < 0) {
            String message = String.format("Category %s not found", category);
            result.addMessage(message, ResultType.NOT_FOUND);
            return result;
        }

        GameQuestion gameQuestion = gameQuestionRepository.create(gameCode, category);
        result.setPayload(gameQuestion);
        return result;
    }

}
