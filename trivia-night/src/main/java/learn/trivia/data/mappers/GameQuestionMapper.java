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
        // FIXME this doesn't have a question column currently, so we need to get the question from the question repo
        // Would it be bad to do this in the controller?
//        gameQuestion.setQuestion(resultSet.getString("question"));
        return gameQuestion;
    }
}
