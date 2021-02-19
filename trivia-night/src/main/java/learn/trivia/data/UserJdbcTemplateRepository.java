package learn.trivia.data;

import learn.trivia.data.mappers.UserMapper;
import learn.trivia.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        final String sql = "select user_id, username, password, total_questions_answered, total_questions_correct "
                + "from user;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findById(int userId) {

        final String sql = "select user_id, username, password, total_questions_answered, total_questions_correct "
                + "from user "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new UserMapper(), userId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public User findByUserName(String userName) {

        final String sql = "select user_id, username, password, total_questions_answered, total_questions_correct "
                + "from user "
                + "where username = ?;";

        return jdbcTemplate.query(sql, new UserMapper(), userName).stream()
                .findFirst().orElse(null);
    }

    @Override
    public User create(User user) {

        final String sql = "insert into user (username, password, total_questions_answered, total_questions_correct) "
                + "values (?,?,0,0);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public boolean update(User user) {

        final String sql = "update user set "
                + "username = ?, "
                + "password = ?, "
                + "total_questions_answered = ?, "
                + "total_questions_correct = ? "
                + "where user_id = ?;";

        return jdbcTemplate.update(sql,
                user.getUserName(),
                user.getPassword(),
                user.getNumAnswered(),
                user.getNumCorrect(),
                user.getUserId()) > 0;
    }

    @Override
    public boolean delete(int userId) {
        return jdbcTemplate.update("delete from user where user_id = ?;", userId) > 0;
    }

    // TODO add in method for leaderboard info
}
