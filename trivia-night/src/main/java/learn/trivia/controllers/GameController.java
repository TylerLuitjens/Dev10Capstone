package learn.trivia.controllers;

import learn.trivia.domain.*;
import learn.trivia.models.Game;
import learn.trivia.models.GameQuestion;
import learn.trivia.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    GameService gameService;
    UserService userService;
    QuestionService questionService;
    GameQuestionService gameQuestionService;
    GameUserService gameUserService;

    public GameController(GameService gameService, UserService userService,
                          GameQuestionService gameQuestionService, GameUserService gameUserService,
                          QuestionService questionService) {

        this.gameService = gameService;
        this.userService = userService;
        this.questionService = questionService;
        this.gameQuestionService = gameQuestionService;
        this.gameUserService = gameUserService;
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable String gameId) {
        return gameService.findGameByCode(gameId);
    }

    @PostMapping("/{category}")
    public ResponseEntity<Object> createGame(@PathVariable String category, @RequestBody User user) {
        Result result = gameService.createGame();

        if (!result.isSuccess()) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }


}