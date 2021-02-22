package learn.trivia.domain;

import learn.trivia.data.QuestionRepository;
import learn.trivia.models.Answer;
import learn.trivia.models.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @MockBean
    QuestionRepository questionRepository;

    // should find by category
    @Test
    void shouldFindByCategory() {
        Question firstQuestion = makeQuestion();
        Question secondQuestion = makeQuestion();
        secondQuestion.setQuestionId(2);

        List<Question> expected = List.of(firstQuestion, secondQuestion);
        when(questionRepository.findByCategory("Celebrities")).thenReturn(expected);

        List<Question> actual = questionService.findByCategory("Celebrities");
        assertEquals(actual, expected);
    }

    // should not find by nonexisting category
    @Test
    void shouldNotFindByNonExistingCategory() {

        when(questionRepository.findByCategory("Bunkum")).thenReturn(null);

        List<Question> actual = questionService.findByCategory("Bunkum");
        assertNull(actual);
    }

    // should add question
    @Test
    void shouldAddQuestion() {
        Question question = makeQuestion();
        question.setQuestionId(0);

        Question mockOut = makeQuestion();

        when(questionRepository.addQuestion(question)).thenReturn(mockOut);

        Result<Question> actual = questionService.addQuestion(question);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    // should not add question with id != 0
    @Test
    void shouldNotAddQuestionWithSetId() {
        Question question = makeQuestion();

        Result<Question> actual = questionService.addQuestion(question);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    // should not add question with blank question field
    @Test
    void shouldNotAddQuestionWithBlankQuestionField() {
        Question question = makeQuestion();
        question.setQuestionId(0);
        question.setQuestion("");

        Result<Question> actual = questionService.addQuestion(question);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    // should not add question with blank category
    @Test
    void shouldNotAddQuestionWithBlankCategory() {
        Question question = makeQuestion();
        question.setQuestionId(0);
        question.setCategory("");

        Result<Question> actual = questionService.addQuestion(question);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    Question makeQuestion() {
        Question question = new Question();

        question.setQuestionId(1);
        question.setQuestion("Sample question?");

        Answer answer1 = makeAnswer();
        Answer answer2 = makeAnswer();
        answer2.setAnswerId(2);
        answer2.setCorrect(false);
        answer2.setAnswer("Another answer");

        question.setAnswers(List.of(answer1, answer2));
        question.setCategory("Celebrities");

        return question;
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
