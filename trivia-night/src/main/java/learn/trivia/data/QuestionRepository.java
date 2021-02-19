package learn.trivia.data;

import learn.trivia.models.Question;

import java.util.List;

public interface QuestionRepository {

    List<Question> findByCategory(String category);

    Question findById(int questionId);

    Question addQuestion(Question question);
}
