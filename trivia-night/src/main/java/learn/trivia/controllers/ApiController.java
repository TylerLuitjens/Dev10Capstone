package learn.trivia.controllers;

import learn.trivia.domain.QuestionService;
import learn.trivia.domain.AnswerService;
import learn.trivia.domain.Result;
import learn.trivia.domain.UserService;
import learn.trivia.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ApiController {

    QuestionService questionService;
    AnswerService answerService;
    UserService userService;

    public ApiController (QuestionService questionService, AnswerService answerService, UserService userService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.userService = userService;
    }

    @GetMapping("/user/{userId}")
    public User findUserById (@PathVariable int userId) {
        User user = userService.findById (userId);

        return user;
    }

    @GetMapping("/user/")
    public List<User> findAllUsers() {
        List<User> allUsers = userService.findAll();
        return allUsers;
    }

    @PostMapping("/user/")
    public ResponseEntity<Object> createUser (@RequestBody User user) {
        Result<User> result = userService.create(user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/user/")
    public ResponseEntity<Object> updateUser (User user) {
        Result<User> result = userService.update(user);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }


}
