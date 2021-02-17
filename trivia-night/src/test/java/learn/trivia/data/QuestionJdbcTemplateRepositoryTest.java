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
        for (Question q : questions) {
            System.out.println(q.getQuestion());
        }
        assertTrue(questions.size() > 0);
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