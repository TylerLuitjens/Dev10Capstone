//package learn.trivia.data;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
//class AnswerJdbcTemplateRepositoryTest {
//
//    @Autowired
//    AnswerJdbcTemplateRepository repository;
//
//    @Autowired
//    KnownGoodState knownGoodState;
//
//    @BeforeEach
//    void setup() {
//        knownGoodState.set();
//    }
//
//    @Test
//    void shouldFindAnswerByQuestionId() {
//        List<Answer> answers = repository.findByQuestionId(1);
//        assertTrue(answers.size > 0);
//    }
//
//    @Test
//    void shouldFindAnswerByAnswerId() {
//        Answer answer = repository.findByAnswerId(1);
//        assertNotNull(answer);
//    }
//
//}