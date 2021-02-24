package learn.trivia.data;

import learn.trivia.models.GameUser;

import java.util.List;

public interface GameUserRepository {

    public GameUser findGameUser(String gameCode, int userId);
    public List<GameUser> findByGameCode(String gameCode);
    public boolean addGameUser(String gameCode, int userId);
    public boolean addGameUser(GameUser gameUser);
    public boolean updateGameUser(GameUser gameUser);
}
