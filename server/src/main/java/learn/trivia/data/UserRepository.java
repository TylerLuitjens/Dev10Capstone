package learn.trivia.data;

import learn.trivia.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(int userId);

    User findByUserName(String userName);

    User create(User user);

    boolean update(User user);

    @Transactional
    boolean delete(int userId);

    List<User> leaderboard();

}
