package learn.trivia.data.mappers;


import learn.trivia.models.GameQuestion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameQuestionMapper implements RowMapper<GameQuestion> {
    @Override
    public GameQuestion mapRow(ResultSet resultSet, int i) throws SQLException {

        GameQuestion gameQuestion = new GameQuestion();
        gameQuestion.setQuestionId(resultSet.getInt("question_id"));
        gameQuestion.setGameCode(resultSet.getString("game_code"));
        gameQuestion.setQuestion(resultSet.getString("question"));

        return gameQuestion;
    }
}
