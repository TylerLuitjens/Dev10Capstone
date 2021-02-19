package learn.trivia.data;

import learn.trivia.models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class QuestionJdbcTemplateRepositoryTest {

    @Autowired
    QuestionJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindCelebritiesCategory() {
        List<Question> questions = repository.findByCategory("Celebrities");
        assertEquals(2, questions.size());
    }

    @Test
    void shouldFindQuestionById() {
        Question question = repository.findById(1);
        assertNotNull(question);
        assertEquals("Paul McCartney has always used his middle name. What is his real first name?",
                question.getQuestion());
        assertEquals("Celebrities", question.getCategory());
    }

    @Test
    void shouldFindNullById() {
        Question question = repository.findById(100);
        assertNull(question);
    }

    @Test
    void shouldAddQuestion() {
        Question question = makeQuestion();
        Question actual = repository.addQuestion(question);
        assertNotNull(actual);
    }

    Question makeQuestion() {
        Question question = new Question();
        question.setQuestion("What is your name?");

        return question;
    }
}