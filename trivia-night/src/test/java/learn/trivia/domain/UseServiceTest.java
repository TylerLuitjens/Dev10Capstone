package learn.trivia.domain;

import learn.trivia.data.UserRepository;
import learn.trivia.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UseServiceTest {

    @Autowired
    UserService service;

    @MockBean
    UserRepository userRepository;


    @Test
    void shouldFindAll() {
        User firstUser = makeUser();
        User secondUser = makeUser();
        secondUser.setUserId(2);
        secondUser.setUserName("Another name");

        List<User> expected = List.of(firstUser, secondUser);

        when(userRepository.findAll()).thenReturn(expected);

        List<User> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        User expected = makeUser();
        when(userRepository.findById(1)).thenReturn(expected);

        User actual = service.findById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByInvalidId() {
        when(userRepository.findById(3000)).thenReturn(null);

        User actual = service.findById(3000);
        assertNull(actual);
    }

    @Test
    void shouldCreateUser() {
        User user = makeUser();

        user.setUserId(0);

        User mockOut = makeUser();

        when(userRepository.create(user)).thenReturn(mockOut);

        Result<User>actual = service.create(user);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut.getNumAnswered(), actual.getPayload().getNumAnswered());
    }

    @Test
    void shouldNotCreateUserWithInvalidId() {
        User user = makeUser();

        Result<User> result = service.create(user);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotCreateDuplicateUser() {
        User firstUser = makeUser();

        when(userRepository.findAll()).thenReturn(List.of(firstUser));

        User resultUser = makeUser();
        resultUser.setUserId(0);

        Result<User> result = service.create(resultUser);

        assertFalse(result.isSuccess());

    }

    // should not add with blank userName
    @Test
    void shouldNotCreateUserWithBlankUserName() {
        User user = makeUser();

        user.setUserId(0);
        user.setUserName("");

        Result<User>actual = service.create(user);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    // should not add with blank password
    @Test
    void shouldNotCreateUserWithBlankPassword() {
        User user = makeUser();

        user.setUserId(0);
        user.setPassword("");

        Result<User>actual = service.create(user);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    // should not add with numAnswered < 0
    @Test
    void shouldNotCreateUserWithNegativeNumAnswered() {
        User user = makeUser();

        user.setUserId(0);
        user.setNumAnswered(-1);

        Result<User>actual = service.create(user);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    // should not add with numCorrect < 0
    @Test
    void shouldNotCreateUserWithNegativeNumCorrect() {
        User user = makeUser();

        user.setUserId(0);
        user.setNumCorrect(-1);

        Result<User>actual = service.create(user);
        assertEquals(ResultType.INVALID, actual.getType());
    }


    @Test
    void shouldUpdate() {
        User user = makeUser();
        when (userRepository.update(user)).thenReturn(true);

        Result<User> result = service.update(user);

        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(user.getNumCorrect(), result.getPayload().getNumCorrect());
    }

    @Test
    void shouldNotUpdateDuplicateName(){
        User user = makeUser();
        when(userRepository.findAll()).thenReturn(List.of(user));

        User resultUser = makeUser();
        resultUser.setNumCorrect(0);
        Result<User> result = service.update(resultUser);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateInvalidId() {
        User user = makeUser();
        user.setUserId(300);
        when(userRepository.findAll()).thenReturn(List.of(user));

        User resultUser = makeUser();
        Result<User> result = service.update(resultUser);

        assertFalse(result.isSuccess());
    }


    User makeUser() {
        User user = new User();
        user.setUserId(1);
        user.setUserName("Test");
        user.setPassword("Pass");
        user.setNumAnswered(2);
        user.setNumCorrect(1);

        return user;
    }
}
