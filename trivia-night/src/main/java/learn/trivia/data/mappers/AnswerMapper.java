package learn.trivia.data.mappers;

import learn.trivia.models.Answer;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
        Answer answer = new Answer();
        answer.setAnswerId(resultSet.getInt("answer_id"));
        int correctInt = (resultSet.getInt("isCorrect"));
        answer.setCorrect(correctInt == 1);
        return answer;
    }
}
