package learn.trivia.domain;

import learn.trivia.data.AnswerJdbcTemplateRepository;
import learn.trivia.data.AnswerRepository;
import learn.trivia.models.Answer;
import learn.trivia.models.User;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class AnswerService {
    private final AnswerRepository repository;

    public AnswerService(AnswerRepository repository) {
        this.repository = repository;
    }

    public List<Answer> findByQuestionId(int questionId) {
        return repository.findByQuestionId(questionId);
    }

    public Answer findByAnswerId(int answerId) {
        return repository.findByAnswerId(answerId);
    }

    public Result<Answer> addAnswer(Answer answer) {
        Result<Answer> result = validate(answer);

        if (!result.isSuccess()) {
            return result;
        }

        if (answer.getAnswerId() != 0) {
            result.addMessage("Id cannot be set for 'add' operation.", ResultType.INVALID);
            return result;
        }

        answer = repository.addAnswer(answer);
        result.setPayload(answer);
        return result;
    }

    private Result<Answer> validate (Answer answer) {
        Result<Answer> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Answer>> violations = validator.validate(answer);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Answer> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
            return result;
        }
        return result;
    }
}
