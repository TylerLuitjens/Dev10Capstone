package learn.trivia.domain;

import learn.trivia.data.GameQuestionRepository;
import learn.trivia.data.GameRepository;
import learn.trivia.data.QuestionRepository;
import learn.trivia.models.Answer;
import learn.trivia.models.Game;
import learn.trivia.models.GameQuestion;
import learn.trivia.models.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GameQuestionServiceTest {

    @Autowired
    GameQuestionService service;

    @MockBean
    GameQuestionRepository gameQuestionRepository;

    @MockBean
    QuestionRepository questionRepository;

    @MockBean
    GameRepository gameRepository;


    // should find by gamecode
    @Test
    void shouldFindByGameCode() {
        GameQuestion gameQuestion = makeGameQuestion();
        GameQuestion gameQuestion2 = makeGameQuestion();
        gameQuestion2.setQuestion("New Question");
        gameQuestion2.setQuestionId(2);

        List<GameQuestion> expected = List.of(gameQuestion, gameQuestion2);

        when(gameQuestionRepository.findByGameCode("WXyZ")).thenReturn(expected);

        List<GameQuestion> actual = service.findByGameCode("WXyZ");
        assertEquals(expected, actual);
    }

    // should not find by nonexisting game code
    @Test
    void shouldNotFindByGameCode() {
        when(gameQuestionRepository.findByGameCode("ABcD")).thenReturn(null);

        List<GameQuestion> actual = service.findByGameCode("ABcD");
        assertNull (actual);
    }

    // should create game question
    @Test
    void shouldCreateGameQuestion() {
        GameQuestion gameQuestion = new GameQuestion();

        when(gameRepository.findGameByCode("WXyZ")).thenReturn(makeGame());
        when(questionRepository.findById(1)).thenReturn(makeQuestion());
        when(gameQuestionRepository.addGameQuestion("WXyZ", 1)).thenReturn(true);

        Result<GameQuestion> actual = service.createGameQuestion("WXyZ", 1);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    // should not create game question with blank game code
    @Test
    void shouldNotCreateGameQuestionWithBlankCode() {
        when(gameQuestionRepository.addGameQuestion("", 1)).thenReturn(false);

        Result<GameQuestion> actual = service.createGameQuestion("", 1);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    // should not create game question with invalid code
    @Test
    void shouldNotCreateGameQuestionWithInvalidCode() {
        when(gameQuestionRepository.addGameQuestion("ZZYX", 1)).thenReturn(false);

        Result<GameQuestion> actual = service.createGameQuestion("ZZYX", 1);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

    // should not create game question with invalid question ID
    @Test
    void shouldNotCreateGameQuestionWithInvalidQuestion() {
        when(gameQuestionRepository.addGameQuestion("WXyZ", 1500000)).thenReturn(false);

        Result<GameQuestion> actual = service.createGameQuestion("WXyZ", 1500000);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }



    GameQuestion makeGameQuestion() {
        GameQuestion gameQuestion = new GameQuestion();

        gameQuestion.setGameCode("WXyZ");
        gameQuestion.setQuestion("What is a question?");
        gameQuestion.setQuestionId(1);

        return gameQuestion;
    }

    Game makeGame() {

        Game game = new Game();

        game.setGameCode("WXyZ");

        return game;
    }

    Question makeQuestion() {
        Question question = new Question();

        question.setQuestionId(1);
        question.setQuestion("Sample question?");
        question.setCategory("Celebrities");

        return question;
    }
}
//
//import learn.trivia.data.GameQuestionRepository;
//import learn.trivia.data.GameRepository;
//import learn.trivia.data.QuestionRepository;
//import learn.trivia.domain.GameQuestionService;
//import learn.trivia.models.Game;
//import learn.trivia.models.GameQuestion;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//public class GameQuestionServiceTest {
//
//    @Autowired
//    GameQuestionService service;
//
//    @MockBean
//    GameQuestionRepository gameQuestionRepository;
//
//    @MockBean
//    QuestionRepository questionRepository;
//
//    @MockBean
//    GameRepository gameRepository;
//

    // should find by gamecode
//    @Test
//    void shouldFindByGameCode() {
//        GameQuestion gameQuestion = makeGameQuestion();
//        GameQuestion gameQuestion2 = makeGameQuestion();
//        gameQuestion2.setQuestion("New Question");
//        gameQuestion2.setQuestionId(2);
//
//        List<GameQuestion> expected = List.of(gameQuestion, gameQuestion2);
//
//        when(gameQuestionRepository.findByGameCode("WXyz")).thenReturn(expected);
//
//        List<GameQuestion> actual = service.findByGameCode("WXyZ");
//        assertEquals(expected, actual);
//    }
//
//    // should not find by nonexisting game code
//    @Test
//    void shouldNotFindByInvalidGameCode() {
//        when(gameQuestionRepository.findByGameCode("ABcD")).thenReturn(null);
//
//        List<GameQuestion> actual = service.findByGameCode("ABcD");
//        assertNull (actual);
//    }
//
//    // should create game question
//    @Test
//    void shouldCreateGameQuestion() {
//        GameQuestion gameQuestion = new GameQuestion();
//
//        when(gameQuestionRepository.addGameQuestion("WXyZ", 1)).thenReturn(true);
//
//        Result<GameQuestion> actual = service.createGameQuestion("WXyZ", 1);
//        assertEquals(ResultType.SUCCESS, actual.getType());
//        assertEquals(gameQuestion, actual.getPayload());
//    }
//
//    // should not create game question with blank game code
//    @Test
//    void shouldNotCreateGameQuestionWithBlankCode() {
//        when(gameQuestionRepository.addGameQuestion("", 1)).thenReturn(null);
//
//        Result<GameQuestion> actual = service.createGameQuestion("", 1);
//        assertEquals(ResultType.INVALID, actual.getType());
//    }
//
//    // should not create game question with invalid code
//    @Test
//    void shouldNotCreateGameQuestionWithInvalidCode() {
//        when(gameQuestionRepository.addGameQuestion("ZZYX", 1)).thenReturn(null);
//
//        Result<GameQuestion> actual = service.createGameQuestion("ZZYX", 1);
//        assertEquals(ResultType.INVALID, actual.getType());
//    }
//
//    // should not create game question with invalid category name
//    @Test
//    void shouldNotCreateGameQuestionWithInvalidQuestionId() {
//        when(gameQuestionRepository.addGameQuestion("WXyZ", 1000)).thenReturn(null);
//
//        Result<GameQuestion> actual = service.createGameQuestion("WXyZ", 1000);
//        assertEquals(ResultType.INVALID, actual.getType());
//    }
//
//    // should add all game questions
//
//
//
//    GameQuestion makeGameQuestion() {
//        GameQuestion gameQuestion = new GameQuestion();
//
//        gameQuestion.setGameCode("WXyZ");
//        gameQuestion.setQuestion("What is a question?");
//        gameQuestion.setQuestionId(1);
//
//        return gameQuestion;
//    }
//}
