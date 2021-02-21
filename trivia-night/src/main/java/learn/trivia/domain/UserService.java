package learn.trivia.domain;

import learn.trivia.data.UserRepository;
import learn.trivia.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(int userId) {
        return repository.findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = repository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException(userName + " not found.");
        }

        List<String> roles = List.of(user.getRole());

        List<GrantedAuthority> authorities = roles.stream()
                .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails
                .User(user.getUserName(), user.getPassword(), authorities);
    }

    public Result<User> create(User user) {
        Result<User> result = domainValidation(user);

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

        user.setPassword(encoder.encode(user.getPassword()));

        user = repository.create(user);
        result.setPayload(user);
        return result;
    }

    public Result<User> update(User user) {
        Result<User> result = domainValidation(user);

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

    private Result<User> domainValidation(User user) {
        Result<User> result = new Result<>();

        if (user.getUserName() == null || user.getUserName().isBlank()) {
            result.addMessage("Username is required.", ResultType.INVALID);
        }

        if (user.getUserName().length() > 30) {
            result.addMessage("Username cannot be more than 30 characters.", ResultType.INVALID);
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            result.addMessage("Password is required.", ResultType.INVALID);
        }

        if (user.getNumAnswered() < 0) {
            result.addMessage("Questions answered cannot be less than 0.", ResultType.INVALID);
        }

        if (user.getNumCorrect() < 0) {
            result.addMessage("Questions correct cannot be less than 0", ResultType.INVALID);
        }

        return result;
    }

    public List<User> leaderboard() {
        return repository.leaderboard();
    }


}
