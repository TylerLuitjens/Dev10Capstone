package learn.trivia.domain;

import learn.trivia.data.AnswerRepository;
import learn.trivia.data.QuestionRepository;
import learn.trivia.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameQuestionRepository gameQuestionRepository;
    private final GameUserRepository gameUserRepository;

    public GameService (
            QuestionRepository questionRepository,
            AnswerRepository answerRepository,
            UserRepository userRepository,
            GameRepository gameRepository,
            GameQuestionRepository gameQuestionRepository,
            GameUserRepository gameUserRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.gameQuestionRepository = gameQuestionRepository;
        this.gameUserRepository = gameUserRepository;
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }


    public Game findGameByCode(String gameCode) {
        return gameRepository.findGameByCode(gameCode);
    }

    public GameUser findGameUser(String gameCode, int userId) {
        return gameUserRepository.findGameUser(gameCode, userId);
    }

    public List<GameQuestion> findByGameCode(String gameCode) {
        return gameQuestionRepository.findByGameCode(gameCode);
    }

    public Result<Game> createGame() {
        Result<Game> result = new Result<>();
        String gameCode = gameCodeGenerator();

        for (Game game : findAllGames()) {
            while (gameCode.equals(game.getGameCode())) {
                gameCode = gameCodeGenerator();
            }
        }

        Game game = gameRepository.createGame(gameCode);
        result.setPayload(game);
        return result;
    }

    public Result<GameQuestion> createGameQuestion(String gameCode, String category) {
        Result<GameQuestion> result = new Result<>();

        for (Game game : findAllGames()) {
            if (gameCode.equals(game.getGameCode())) {
                if (questionRepository.findByCategory(category).size() > 0) {
                    GameQuestion gameQuestion = gameQuestionRepository.create(gameCode, category);
                    result.setPayload(gameQuestion);
                    return result;
                }
                String message = String.format("Category %s not found", category);
                result.addMessage(message, ResultType.INVALID);
                return result;
            }
        }
        String message = String.format("Game code %s not found", gameCode);
        result.addMessage(message, ResultType.INVALID);
        return result;
    }

    public Result<GameUser> createGameUser(String gameCode, int userId) {
        Result<GameUser> result = validate(userId);

        for (Game game : findAllGames()) {
            if (gameCode.equals(game.getGameCode())) {
                if (userRepository.findById(userId) != null) {
                    GameUser gameUser = gameUserRepository.create(gameCode, userId);
                    result.setPayload(gameUser);
                    return result;
                }
                String message = String.format("User with id %s not found", userId);
                result.addMessage(message, ResultType.INVALID);
                return result;
            }
        }
        String message = String.format("Game code %s not found", gameCode);
        result.addMessage(message, ResultType.INVALID);
        return result;
    }

    public Result<GameUser> updateGameUser(String gameCode, int userId, int numAnswered, int numCorrect) {
        Result<GameUser> result = validate(userId);

        for (Game game : findAllGames()) {
            if (gameCode.equals(game.getGameCode())) {
                if (userRepository.findById(userId) != null) {
                    GameUser gameUser = findGameUser(gameCode, userId);

                    gameUser.setNumAnswered(numAnswered);
                    gameUser.setNumCorrect(numCorrect);

                    GameUser gameUserUpdated = gameUserRepository.update(gameUser);

                    result.setPayload(gameUserUpdated);
                    return result;
                }
                String message = String.format("User with id %s not found", userId);
                result.addMessage(message, ResultType.INVALID);
                return result;
            }
        }
        String message = String.format("Game code %s not found", gameCode);
        result.addMessage(message, ResultType.INVALID);
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

    private Result<GameUser> validate(int userId) {
        Result<GameUser> result = new Result<>();

        if (userId <= 0) {
            result.addMessage("User Id must be set for this operation.", ResultType.INVALID);
            return result;
        }
        return result;
    }

}
