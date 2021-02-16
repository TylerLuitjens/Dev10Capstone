package learn.trivia.data.mappers;

import javax.swing.tree.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
        Answer answer = Answer();
        answer.setAnswerId(resultSet.getInt(""));
        answer.setIsCorrect(resultSet.getInt(""));
        return answer;
    }
}
