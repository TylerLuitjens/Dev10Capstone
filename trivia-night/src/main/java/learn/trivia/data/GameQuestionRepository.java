package learn.trivia.data;

import learn.trivia.models.GameQuestion;

import java.util.List;

public interface GameQuestionRepository {

    public GameQuestion getGameQuestion (String gameCode, int questionId);
    public List<GameQuestion> findByGameCode(String gameCode);
    public boolean addGameQuestion (String gameCode, int questionId);
    public boolean addGameQuestion (GameQuestion gameQuestion);
    public boolean addAll (List<GameQuestion> gameQuestions);
}
