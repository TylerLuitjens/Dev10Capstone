package learn.trivia.controllers;

import learn.trivia.domain.*;
import learn.trivia.models.*;
import org.springframework.http.HttpEntity;
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
        // TODO get gameQuestions, and gameUsers as well, tie them to the game, and then return it
        return gameService.findGameByCode(gameId);
    }

    @PostMapping("/user/{userId}/{gameCode}")
    public ResponseEntity<Object> addUserToGame(@PathVariable int userId, @PathVariable String gameCode) {
        Result<GameUser> result = gameUserService.createGameUser(gameCode, userId);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
        }

        Game game = gameService.findGameByCode(gameCode);
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @PostMapping("/{category}")
    public ResponseEntity<Object> createGame(@PathVariable String category, @RequestBody User user) {
        Result<Game> result = gameService.createGame();

        if (!result.isSuccess()) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
        }

        Game game = (Game) result.getPayload(); // FIXME is there a better way than casting here?
        gameQuestionService.createGameQuestion(game.getGameCode(), category);
        gameUserService.createGameUser(game.getGameCode(), user.getUserId());

        game = populateGame(game);

        if (game.getGameUsers().size() == 0 || game.getGameQuestions().size() == 0) {
            return new ResponseEntity<>(ErrorResponse.build(result), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    // FIXME might not need, delete later
    private List<GameQuestion> transformQuestions (List<Question> questions, String gameCode) {
        List<GameQuestion> gameQuestions = new ArrayList<GameQuestion>();

        for(Question question : questions) {
            GameQuestion gameQuestion = new GameQuestion();
            gameQuestion.setQuestionId(question.getQuestionId());
            gameQuestion.setGameCode(gameCode);
            gameQuestion.setQuestion(question.getQuestion());

            gameQuestions.add(gameQuestion);
        }
        return gameQuestions;
    }

    //FIXME might not need, delete later
    private List<GameUser> transformUsers (List<User> users, String gameCode) {
        List<GameUser> gameUsers = new ArrayList<GameUser>();

        for(User user : users) {
            GameUser gameUser = new GameUser();
            gameUser.setUserId(user.getUserId());
            gameUser.setGameCode(gameCode);
            gameUser.setNumCorrect(0);
            gameUser.setNumAnswered(0);

            gameUsers.add(gameUser);
        }
        return gameUsers;
    }

    private Game populateGame(Game game) {
        List<GameQuestion> gameQuestions =  gameQuestionService.findByGameCode(game.getGameCode());
        List<GameUser> gameUsers = gameUserService.findByGameCode(game.getGameCode());
        game.setGameQuestions(gameQuestions);
        game.setGameUsers(gameUsers);

        return game;
    }
}