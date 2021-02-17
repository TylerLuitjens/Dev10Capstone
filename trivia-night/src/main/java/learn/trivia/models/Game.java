package learn.trivia.models;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class Game {
    private String gameCode;
    private List<GameQuestion> gameQuestions;
    private List<GameUser> gameUsers;

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public List<GameQuestion> getGameQuestions() {
        return gameQuestions;
    }

    public void setGameQuestions(List<GameQuestion> gameQuestions) {
        this.gameQuestions = gameQuestions;
    }

    public List<GameUser> getGameUsers() {
        return gameUsers;
    }

    public void setGameUsers(List<GameUser> gameUsers) {
        this.gameUsers = gameUsers;
    }
}
