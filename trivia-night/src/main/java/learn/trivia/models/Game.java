package learn.trivia.models;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class Game {
    private List<Question> gameQuestions;
    private List<User> gameUsers;
    @NotBlank
    private String gameCode;

    public List<Question> getGameQuestions() {
        return gameQuestions;
    }

    public void setGameQuestions(List<Question> gameQuestions) {
        this.gameQuestions = gameQuestions;
    }

    public List<User> getGameUsers() {
        return gameUsers;
    }

    public void setGameUsers(List<User> gameUsers) {
        this.gameUsers = gameUsers;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }
}
