//package learn.trivia.controllers;
//
//import learn.trivia.domain.GameService;
//import learn.trivia.domain.Result;
//import learn.trivia.domain.UserService;
//import learn.trivia.models.Game;
//import learn.trivia.models.User;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//public class ApiController {
//
//    GameService gameService;
//    UserService userService;
//
//    public ApiController (GameService gameService, UserService userService) {
//        this.gameService = gameService;
//        this.userService = userService;
//    }
//
//    @GetMapping("/user/{userId}")
//    public User findUserById (@PathVariable int userId) {
//        return userService.findById (userId);
//    }
//
//    @GetMapping("/user/")
//    public List<User> findAllUsers() {
//        return userService.findAll();
//    }
//
//    @PostMapping("/user/")
//    public ResponseEntity<Object> createUser (@RequestBody User user) {
//        Result result  = userService.create(user);
//
//        if (!result.isSuccess()) {
//            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/user/")
//    public ResponseEntity<Object> updateUser (User user) {
//        Result result = userService.update(user);
//
//        if (!result.isSuccess()) {
//            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @GetMapping("/game/{gameId}")
//    public Game getGame(@PathVariable String gameId) {
//        return gameService.getGame(gameId);
//    }
//
//    @PostMapping("/game/{category}")
//    public ResponseEntity<Object> createGame(@PathVariable String category) {
//        Result result = gameService.create(category);
//
//        if (!result.isSuccess()) {
//            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
//    }
//
//
//}
