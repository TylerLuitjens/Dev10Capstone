package learn.trivia.data;

import learn.trivia.models.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(int userId);

    User create(User user);

    boolean update(User user);

    boolean delete(int userId);


}
