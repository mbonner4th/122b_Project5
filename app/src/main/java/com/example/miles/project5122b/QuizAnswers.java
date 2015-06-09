package com.example.miles.project5122b;

/**
 * Created by Lawrence on 6/8/2015.
 */
public class QuizAnswers {
    private String answer;
    private boolean isCorrectAnswer;

    public QuizAnswers() {
        this.isCorrectAnswer =false;
        this.answer = "None";
    }

    public QuizAnswers(String answer, boolean isCorrectAnswer)
    {
        this.isCorrectAnswer = isCorrectAnswer;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setIsCorrectAnswer(boolean isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
