package learn.trivia.data;

import learn.trivia.data.mappers.UserMapper;
import learn.trivia.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        final String sql = "select user_id, username, password, user_role, total_questions_answered, total_questions_correct "
                + "from user;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User findById(int userId) {

        final String sql = "select user_id, username, user_role, password, total_questions_answered, total_questions_correct "
                + "from user "
                + "where user_id = ?;";

        return jdbcTemplate.query(sql, new UserMapper(), userId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public User findByUserName(String userName) {

        final String sql = "select user_id, username, user_role, password, total_questions_answered, total_questions_correct "
                + "from user "
                + "where username = ?;";

        return jdbcTemplate.query(sql, new UserMapper(), userName).stream()
                .findFirst().orElse(null);
    }

    @Override
    public User create(User user) {

        final String sql = "insert into user (username, password, user_role, total_questions_answered, total_questions_correct) "
                + "values (?,?,'USER',0,0);";

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

    // delete from game_user first
    @Override
    @Transactional
    public boolean delete(int userId) {
        jdbcTemplate.update("delete from game_user where user_id = ?;", userId);
        return jdbcTemplate.update("delete from user where user_id = ?;", userId) > 0;
    }

    @Override
    public List<User> leaderboard() {

        final String sql = "select user_id, username, total_questions_answered, total_questions_correct "
                + "from user "
                + "order by total_questions_correct desc, total_questions_answered asc "
                + "limit 10;";

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setUserName(resultSet.getString("username"));
            user.setNumAnswered(resultSet.getInt("total_questions_answered"));
            user.setNumCorrect(resultSet.getInt("total_questions_correct"));
            return user;
        });
    }

}
