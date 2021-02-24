package learn.trivia.data.mappers;

import learn.trivia.models.Question;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet resultSet, int i) throws SQLException {
        Question question = new Question();
        question.setQuestionId(resultSet.getInt("question_id"));
        question.setQuestion(resultSet.getString("question"));
        question.setCategory(resultSet.getString("category_name"));
        return question;
    }
}
