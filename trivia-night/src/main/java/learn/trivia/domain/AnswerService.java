package learn.trivia.domain;

import org.springframework.stereotype.Service;

@Service
public class AnswerService {
    private AnswerJdbcTemplateRepository repository;

    public AnswerService(AnswerJdbcTemplateRepository repository) {
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
