package learn.trivia.domain;

import learn.trivia.data.QuestionRepository;

public class GameQuestionService {
    GameQuestionRepository gameQuestionRepository;
    GameRepository gameRepository;

    public GameQuestionService(GameQuestionRepository gameQuestionRepository, GameRepository gameRepository) {
        this.gameQuestionRepository = gameQuestionRepository,
        this.gameRepository = gameRepository;
    }


    public Result<GameQuestion> createGameQuestion(String gameCode, String category) {
        Result<GameQuestion> result = new Result<>();

        for (Game game : gameRepository.findAllGames()) {
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

}
