package ru.otus.spring.spring.domain;

import java.util.List;

public class Form {
    private List<Questionnaire> questionnaire;
    private List<String>  columnNames;

    public List<Questionnaire> getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(List<Questionnaire> questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public String toString() {
        return "Form{" +
                "questionnaire=" + questionnaire +
                ", columnNames=" + columnNames +
                '}';
    }
}
