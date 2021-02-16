package learn.trivia.data;

import java.util.List;

public interface QuestionRepository {

    List<Question> findByCategory(int question_id);

    Question addQuestion(Question question);
}
