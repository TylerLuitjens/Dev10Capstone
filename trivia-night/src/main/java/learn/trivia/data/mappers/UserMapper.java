package learn.trivia.data.mappers;

import learn.trivia.models.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setNumAnswered(resultSet.getInt("total_questions_answered"));
        user.setNumCorrect(resultSet.getInt("total_questions_correct"));
        return user;
    }

}
