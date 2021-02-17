package learn.trivia.domain;

import learn.trivia.data.AnswerRepository;
import learn.trivia.models.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private AnswerRepository repository;

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
        Result<Answer> result = new Result<>();

        if (answer.getAnswerId() != 0) {
            result.addMessage("Id cannot be set for 'add' operation.", ResultType.INVALID);
            return result;
        }

        answer = repository.addAnswer(answer);
        result.setPayload(answer);
        return result;
    }
}
