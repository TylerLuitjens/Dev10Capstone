package learn.trivia.data;

import learn.trivia.models.Answer;

import java.util.List;

public interface AnswerRepository {

    List<Answer> findByQuestionId(int questionId);

    Answer findByAnswerId(int answerId);

    Answer findByAnswer(String answer);

    Answer addAnswer(Answer answer);

}
