package learn.trivia.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class User {
    private int userId;
    @NotBlank
    String userName;
    @NotBlank
    String password;
    @Min(0)
    int numAnswered;
    @Min(0)
    int numCorrect;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
