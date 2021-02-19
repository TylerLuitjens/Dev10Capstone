package learn.trivia.domain;

import learn.trivia.data.GameRepository;
import learn.trivia.data.QuestionRepository;
import learn.trivia.models.Game;
import learn.trivia.models.GameQuestion;
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

        when(gameQuestionRepository.findByGameCode("WXyz")).thenReturn(expected);

        List<GameQuestion> actual = service.findByGameCode("WXyZ");
        assertEquals(expected, actual);
    }

    // should not find by nonexisting game code
    @Test
    void shouldFindByGameCode() {
        when(gameQuestionRepository.findByGameCode("ABcD")).thenReturn(null);

        List<GameQuestion> actual = service.findByGameCode("ABcD");
        assertNull (actual);
    }

    // should create game question
    @Test
    void shouldCreateGameQuestion() {
        GameQuestion gameQuestion = new GameQuestion();

        when(gameQuestionRepository.add("WXyZ", "Celebrities").thenReturn(gameQuestion);

        Result<GameQuestion> actual = service.createGameQuestion("WXyZ", "Celebrities");
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(gameQuestion, actual.getPayload());
    }

    // should not create game question with blank game code
    @Test
    void shouldNotCreateGameQuestionWithBlankCode() {
        when(gameQuestionRepository.add("", "Celebrities").thenReturn(null);

        Result<GameQuestion> actual = service.createGameQuestion("", "Celebrities");
        assertEquals(ResultType.INVALID, actual.getType());
    }

    // should not create game question with invalid code
    @Test
    void shouldNotCreateGameQuestionWithInvalidCode() {
        when(gameQuestionRepository.add("ZZYX", "Celebrities").thenReturn(null);

        Result<GameQuestion> actual = service.createGameQuestion("ZZYX", "Celebrities");
        assertEquals(ResultType.INVALID, actual.getType());
    }

    // should not create game question with invalid category name
    @Test
    void shouldNotCreateGameQuestionWithInvalidCategory() {
        when(gameQuestionRepository.add("WXyZ", "Boolean").thenReturn(null);

        Result<GameQuestion> actual = service.createGameQuestion("WXyZ", "Boolean");
        assertEquals(ResultType.INVALID, actual.getType());
    }



    GameQuestion makeGameQuestion() {
        GameQuestion gameQuestion = new GameQuestion();

        gameQuestion.setGameCode("WXyZ");
        gameQuestion.setQuestion("What is a question?");
        gameQuestion.setQuestionId(1);

        return gameQuestion;
    }
}
