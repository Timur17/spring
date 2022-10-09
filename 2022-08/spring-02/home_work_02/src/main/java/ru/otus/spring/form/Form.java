package ru.otus.spring.spring.form;

import ru.otus.spring.spring.questionnaire.Questionnaire;

import java.util.List;

public interface Form {
    public List<Questionnaire> getQuestionnaires();

    public void setQuestionnaires(List<Questionnaire> questionnaires);

    public List<String> getColumnNames();

    public void setColumnNames(List<String> columnNames);

    public void printForm();
}
