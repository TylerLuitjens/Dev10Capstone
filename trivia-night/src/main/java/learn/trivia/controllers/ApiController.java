package learn.trivia.controllers;

import learn.trivia.domain.QuestionService;
import learn.trivia.domain.AnswerService;
import learn.trivia.domain.UserService;
import learn.trivia.models.User;
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
    public User createUser (@RequestBody User user) {
        user = userService.createUser(user);
        return user;
    }

    @PutMapping("/user/")
    public ResponseEntity<Object> updateUser (User user) {
        boolean success = userService.updateUser(user);


    }


}
