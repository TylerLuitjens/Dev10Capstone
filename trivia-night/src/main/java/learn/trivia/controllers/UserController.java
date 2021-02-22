package learn.trivia.controllers;

import learn.trivia.domain.Result;
import learn.trivia.domain.UserService;
import learn.trivia.models.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User findUserById (@PathVariable int userId) {
        return userService.findById (userId);
    }

    @GetMapping("/")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userName}")
    public User findUserByUserName(@PathVariable String userName) {
        return userService.findByUserName(userName);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser (@RequestBody User user) {

        Result<User> result  = userService.create(user);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateUser (@RequestBody User user) {
        Result<User> result = userService.update(user);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO need endpoint to find user by name

    @GetMapping("/leaderboard")
    public List<User> findLeaderboard() {
        return userService.leaderboard();
    }
}
