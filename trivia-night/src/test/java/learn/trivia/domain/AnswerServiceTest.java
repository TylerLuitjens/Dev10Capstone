package learn.trivia.domain;

import learn.trivia.data.AnswerRepository;
import learn.trivia.models.Answer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AnswerServiceTest {

    @Autowired
    AnswerService service;

    @MockBean
    AnswerRepository answerRepository;

    @Test
    void shouldFindByQuestionId() {
        Answer firstAnswer = makeAnswer();
        Answer secondAnswer = makeAnswer();
        secondAnswer.setAnswerId(2);
        secondAnswer.setCorrect(false);

        List<Answer> expected = List.of(firstAnswer, secondAnswer);
        when(answerRepository.findByQuestionId(1)).thenReturn(expected);

        List<Answer> actual = service.findByQuestionId(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByInvalidQuestionId() {
        when(answerRepository.findByQuestionId(300)).thenReturn(null);

        List<Answer> actual = service.findByQuestionId(300);
        assertNull(actual);
    }

    @Test
    void shouldFindByAnswerId() {
        Answer answer = makeAnswer();

        when(answerRepository.findByAnswerId(1)).thenReturn(answer);

        Answer actual = service.findByAnswerId(1);
        assertEquals(answer, actual);
    }

    @Test
    void shouldNotFindByInvalidAnswerId() {
        when(answerRepository.findByAnswerId(500)).thenReturn(null);

        Answer actual = service.findByAnswerId(500);
        assertNull(actual);
    }

    @Test
    void shouldAddAnswer() {
        Answer answer = makeAnswer();
        answer.setAnswerId(0);

        Answer mockOut = makeAnswer();

        when(answerRepository.addAnswer(answer)).thenReturn(mockOut);

        Result<Answer> actual = service.addAnswer(answer);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertTrue(mockOut.getAnswer().equals(actual.getPayload().getAnswer()));
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddAnswerWithInvalidId() {
        Answer answer = makeAnswer();

        Result<Answer> actual = service.addAnswer(answer);

        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldNotAddAnswerWithBlankAnswerField() {
        Answer answer = makeAnswer();
        answer.setAnswerId(0);
        answer.setAnswer("");

        Result<Answer> actual = service.addAnswer(answer);

        assertEquals(ResultType.INVALID, actual.getType());
    }



    Answer makeAnswer() {
        Answer answer = new Answer();

        answer.setAnswer("Random answer");
        answer.setQuestionId(1);
        answer.setAnswerId(1);
        answer.setCorrect(true);

        return answer;
    }
}
