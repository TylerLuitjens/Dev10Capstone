package learn.trivia.data;

import learn.trivia.models.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AnswerJdbcTemplateRepositoryTest {

    @Autowired
    AnswerJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAnswerByQuestionId() {
        List<Answer> answers = repository.findByQuestionId(2);
        assertTrue(answers.size() > 0);
        assertEquals(4, answers.size());
    }

    @Test
    void shouldFindAnswerByAnswerId() {
        Answer answer = repository.findByAnswerId(1);
        assertNotNull(answer);
        assertTrue(answer.isCorrect());
    }

    @Test
    void shouldFindAnswer() {
        Answer answer = repository.findByAnswer("James");
        assertNotNull(answer);
        assertEquals(1, answer.getAnswerId());
        assertTrue(answer.isCorrect());
    }

    @Test
    void shouldNotFindAnswer() {
        Answer answer = repository.findByAnswer("NOT FOUND");
        assertNull(answer);
    }

    @Test
    void shouldAddAnswer() {
        Answer answer = makeAnswer();
        Answer actual = repository.addAnswer(answer);
        assertNotNull(actual);
    }

    private Answer makeAnswer() {
        Answer answer = new Answer();
        answer.setAnswer("Totally Correct");
        answer.setQuestionId(2);
        answer.setCorrect(true);
        return answer;
    }

}