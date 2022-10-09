package ru.otus.spring.spring.questionnaire;

import java.util.List;

public interface Questionnaire {
    void addResponse(String response);

    List<String> getResponses();

    int getId();

     String getQuestion();

    void setQuestion(String question);

    public void setSelectedAnswer(String selectedAnswer);

    public String getSelectedAnswer();
}
