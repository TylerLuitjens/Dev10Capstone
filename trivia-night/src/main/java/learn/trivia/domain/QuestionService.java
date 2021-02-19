package learn.trivia.domain;

import learn.trivia.data.QuestionJdbcTemplateRepository;
import learn.trivia.data.QuestionRepository;
import learn.trivia.models.Answer;
import learn.trivia.models.Question;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class QuestionService {
    private QuestionRepository repository;

    public QuestionService (QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    public Result<Question> addQuestion(Question question) {
        Result<Question> result = validate(question);

        if (!result.isSuccess()) {
            return result;
        }

        if(question.getQuestionId() != 0) {
            result.addMessage("Question Id cannot be set for 'add' operation", ResultType.INVALID);
            return result;
        }

        question = repository.addQuestion(question);
        result.setPayload(question);
        return result;
    }

    private Result<Question> validate (Question question) {
        Result<Question> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Question> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        return result;
    }

}
