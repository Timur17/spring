package ru.otus.spring.questionnaire;

import java.util.ArrayList;
import java.util.List;

public class StudentQuestionnaire implements Questionnaire{
    private final int id;
    private String question;
    private final List<String> responses = new ArrayList<>();
    private String selectedAnswer;

    public StudentQuestionnaire(int id) {
        this.id = id;
    }

    public void addResponse(String response){
        responses.add(response);
    }

    public List<String> getResponses() {
        return responses;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    @Override
    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", responses=" + responses +
                ", selectedAnswer=" + selectedAnswer +
                '}';
    }
}
