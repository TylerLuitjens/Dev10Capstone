package learn.trivia.controllers;

import learn.trivia.domain.*;
import learn.trivia.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    GameService gameService;
    UserService userService;
    QuestionService questionService;
    GameQuestionService gameQuestionService;
    GameUserService gameUserService;
    AnswerService answerService;

    public GameController(GameService gameService, UserService userService,
                          GameQuestionService gameQuestionService, GameUserService gameUserService,
                          QuestionService questionService, AnswerService answerService) {

        this.gameService = gameService;
        this.userService = userService;
        this.questionService = questionService;
        this.gameQuestionService = gameQuestionService;
        this.gameUserService = gameUserService;
        this.answerService = answerService;
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable String gameId) {
        return populateGame(gameService.findGameByCode(gameId));
    }

    @PostMapping("/user/{userId}/{gameCode}")
    public ResponseEntity<Object> addUserToGame(@PathVariable int userId, @PathVariable String gameCode) {
        // Verify that we don't already have current user in the game. We don't want to add a user twice to a game.
        boolean userFound = false;
        for (GameUser user : gameUserService.findByGameCode(gameCode)) {
            if (user.getUserId() == userId) {
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            Result<GameUser> result = gameUserService.createGameUser(gameCode, userId);
            if (!result.isSuccess()) {
                return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
            }
        }

        Game game = gameService.findGameByCode(gameCode);
        game = populateGame(game);
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @GetMapping("/gameusers/{gameCode}")
    public List<GameUser> getGameUsers(@PathVariable String gameCode) {
        return gameUserService.findByGameCode(gameCode);
    }

    @GetMapping("/games/{userId}")
    public List<GameUser> getGamesByUser(@PathVariable int userId) {
        return gameUserService.findByUser(userId);
    }

    @PostMapping("/{category}")
    public ResponseEntity<Object> createGame(@PathVariable String category, @RequestBody User user) {
        Result<Game> result = gameService.create();

        if (!result.isSuccess()) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
        }

        Game game = (Game) result.getPayload();
        List<Question> questions = questionService.findByCategory(category);
        List<GameQuestion> gameQuestions = transformQuestions(questions, game.getGameCode());
        boolean success = gameQuestionService.addAll(gameQuestions);

        if (!success) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
        }

        gameUserService.createGameUser(game.getGameCode(), user.getUserId());

        game = populateGame(game);

        if (game.getGameUsers().size() == 0 || game.getGameQuestions().size() == 0) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    private List<GameQuestion> transformQuestions (List<Question> questions, String gameCode) {
        List<GameQuestion> gameQuestions = new ArrayList<GameQuestion>();

        for(Question question : questions) {
            GameQuestion gameQuestion = new GameQuestion();
            gameQuestion.setQuestionId(question.getQuestionId());
            gameQuestion.setGameCode(gameCode);
            gameQuestion.setQuestion(question.getQuestion());
            gameQuestion.setAnswers(answerService.findByQuestionId(question.getQuestionId()));
            gameQuestions.add(gameQuestion);
        }
        return gameQuestions;
    }

    private Game populateGame(Game game) {

        if (game == null) {
            System.out.println("Unable to find game.");
            return null;
        } else if (game.getGameCode() == null) {
            System.out.println("Invalid game code");
            return null;
        }
        List<GameQuestion> gameQuestions =  gameQuestionService.findByGameCode(game.getGameCode());
        for (GameQuestion question : gameQuestions) {
            question.setAnswers(answerService.findByQuestionId(question.getQuestionId()));
        }
        List<GameUser> gameUsers = gameUserService.findByGameCode(game.getGameCode());
        game.setGameQuestions(gameQuestions);
        game.setGameUsers(gameUsers);

        return game;
    }


    @PutMapping("/")
    public ResponseEntity<Object> updateGameUser(@RequestBody GameUser gameUser) {

        User user = userService.findById(gameUser.getUserId());
        user.setNumAnswered(user.getNumAnswered() + gameUser.getNumAnswered());
        user.setNumCorrect((user.getNumCorrect() + gameUser.getNumCorrect()));
        userService.update(user);
        Result<GameUser> result = gameUserService.updateGameUser(gameUser);

        if (!result.isSuccess()) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }









}