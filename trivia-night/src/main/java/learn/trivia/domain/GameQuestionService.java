package learn.trivia.domain;

import learn.trivia.data.GameQuestionRepository;
import learn.trivia.data.GameRepository;
import learn.trivia.data.QuestionRepository;
import learn.trivia.models.GameQuestion;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.List;

@Service
public class GameQuestionService {
//    private final GameQuestionRepository gameQuestionRepository;
//    private final QuestionRepository questionRepository;
//    private final GameRepository gameRepository;
//
//    public GameQuestionService(GameQuestionRepository gameQuestionRepository, QuestionRepository questionRepository,
//                               GameRepository gameRepository) {
//        this.gameQuestionRepository = gameQuestionRepository;
//        this.questionRepository = questionRepository;
//        this.gameRepository = gameRepository;
//    }
//
//    public List<GameQuestion> findByGameCode(String gameCode) {
//        return gameQuestionRepository.findByGameCode(gameCode);
//    }
//
//    public boolean addAll(List<GameQuestion> gameQuestions) {
//        return gameQuestionRepository.addAll(gameQuestions);
//    }
//
//    public Result<GameQuestion> createGameQuestion(String gameCode, int questionId) {
//        Result<GameQuestion> result = new Result<>();
//
//        if (gameRepository.findGameByCode(gameCode) == null) {
//            String message = String.format("Game code %s not found", gameCode);
//            result.addMessage(message, ResultType.NOT_FOUND);
//            return result;
//        }
//
//        if (questionRepository.findById(questionId) == null) {
//            String message = String.format("Question %s not found", questionId);
//            result.addMessage(message, ResultType.NOT_FOUND);
//            return result;
//        }
//
//        boolean success = gameQuestionRepository.addGameQuestion(gameCode, questionId);
//
//        if (!success) {
//            result.addMessage("Database error. Unable to add User to Game", ResultType.INVALID);
//        }
//
//        return result;
//    }

}