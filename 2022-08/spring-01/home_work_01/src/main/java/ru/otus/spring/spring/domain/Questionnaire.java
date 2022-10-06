package ru.otus.spring.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Questionnaire {
    private final int id;
    private String question;
    private final List<String> responses = new ArrayList<>();

    public Questionnaire(int id) {
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

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", responses=" + responses +
                '}';
    }
}
