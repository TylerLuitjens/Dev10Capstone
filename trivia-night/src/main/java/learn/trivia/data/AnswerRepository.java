package learn.trivia.data;

public interface AnswerRepository {

    List<Answer> findByQuestionId(int questionId);

    Answer findByAnswerId(int answerId);

    Answer addAnswer(Answer answer);

}
