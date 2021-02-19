package learn.trivia.data;

import learn.trivia.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {

    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<User> users = repository.findAll();
        assertNotNull(users);
        for (User u : users) {
            System.out.println(u.getUserName());
        }
    }

    @Test
    void shouldFindFirstUser() {
        User firstUser = repository.findById(1);
        assertEquals(1, firstUser.getUserId());
        assertEquals("First User", firstUser.getUserName());
        assertEquals("Clear_text", firstUser.getPassword());
    }

    @Test
    void shouldFindNullById() {
        User user = repository.findById(100);
        assertNull(user);
    }

    @Test
    void shouldFindByUserName() {
        User user = repository.findByUserName("First User");
        assertNotNull(user);
    }

    @Test
    void shouldFindNullByUserName() {
        User user = repository.findByUserName("Non existing");
        assertNull(user);
    }

    @Test
    void shouldCreateUser() {
        User user = makeUser();
        User actual = repository.create(user);
        assertNotNull(actual);
    }

    @Test
    void shouldUpdate() {
        User user = makeUser();
        user.setUserId(2);
        assertTrue(repository.update(user));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.delete(3));
        assertFalse(repository.delete(3));
    }

    private User makeUser() {
        User user = new User();
        user.setUserName("Test Username");
        user.setPassword("Test Password");
        user.setNumAnswered(100);
        user.setNumCorrect(1);
        return user;
    }

    @Test
    void shouldFindLeaderboard() {
        List<User> leaderboard = repository.leaderboard();
        assertNotNull(leaderboard);
        assertTrue(leaderboard.size() <= 10);
    }
}