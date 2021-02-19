package learn.trivia.domain;

import learn.trivia.data.UserRepository;
import learn.trivia.models.User;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(int userId) {
        return repository.findById(userId);
    }

    public User findByUserName(String UserName) {
        return repository.findByUserName(UserName);
    }

    public Result<User> create(User user) {
        Result<User> result = validate(user);

        if (!result.isSuccess()) {
            return result;
        }

        result = validateDuplicateCreate(user);

        if (!result.isSuccess()) {
            return result;
        }

        if (user.getUserId() != 0) {
            result.addMessage("User Id cannot be set for 'create' operation.", ResultType.INVALID);
            return result;
        }

        user = repository.create(user);
        result.setPayload(user);
        return result;
    }

    public Result<User> update(User user) {
        Result<User> result = validate(user);

        if (!result.isSuccess()) {
            return result;
        }

        result = validateDuplicateUpdate(user);

        if (!result.isSuccess()) {
            return result;
        }

        if (user.getUserId() <= 0) {
            result.addMessage("User Id must be set for 'update' operation.", ResultType.INVALID);
            return result;
        }

        if (!repository.update(user)) {
            String message = String.format("User Id %s not found.", user.getUserId());
            result.addMessage(message, ResultType.NOT_FOUND);
        }

        result.setPayload(user);
        return result;
    }

    public boolean delete(int userId) {
        return repository.delete(userId);
    }


    private Result<User> validateDuplicateCreate(User user) {
        Result<User> result = new Result<>();

        for (User userFromRecords : findAll()) {
            if (userFromRecords.getUserName().equalsIgnoreCase(user.getUserName())) {
                result.addMessage("Username cannot be duplicate.", ResultType.INVALID);
            }
        }
        return result;
    }

    private Result<User> validateDuplicateUpdate(User user) {
        Result<User> result = new Result<>();

        User existingUser = findById(user.getUserId());

        if (existingUser == null || !existingUser.getUserName().equalsIgnoreCase(user.getUserName())) {
            result = validateDuplicateCreate(user);
        }

        return result;
    }

    private Result<User> validate (User user) {
        Result<User> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        return result;
    }

    public List<User> leaderboard() {
        return repository.leaderboard();
    }
}
