package learn.trivia.models;

public class GameUser {
    private String gameCode;
    private int numAnswered;
    private int numCorrect;
    private int userId;

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public int getNumAnswered() {
        return numAnswered;
    }

    public void setNumAnswered(int numAnswered) {
        this.numAnswered = numAnswered;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
