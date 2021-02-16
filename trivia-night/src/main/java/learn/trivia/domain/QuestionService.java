package learn.trivia.domain;

import learn.trivia.data.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private QuestionRepository repository;

    public QuestionService (QuestionJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    public List<Question> findByCategory(int categoryId) {
        return repository.findByCategory(categoryId);
    }

    public Result<Question> addQuestion(Question question) {
        Result<Question> result = new Result<>();

        if(question.getQuestionId() != 0) {
            result.addMessage("Question Id cannot be set for 'add' operation", ResultType.INVALID);
            return result;
        }

        question = repository.addQuestion(question);
        result.setPayload(question);
        return result;
    }

}
