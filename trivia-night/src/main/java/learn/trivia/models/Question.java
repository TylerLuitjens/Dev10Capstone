package learn.trivia.models;

import java.util.List;

public class Question {
    private int questionId;
    private String question;
    private List<Answer> answers;
    private String category;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question1 = (Question) o;

        if (questionId != question1.questionId) return false;
        if (question != null ? !question.equals(question1.question) : question1.question != null) return false;
        if (answers != null ? !answers.equals(question1.answers) : question1.answers != null) return false;
        return category != null ? category.equals(question1.category) : question1.category == null;
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
