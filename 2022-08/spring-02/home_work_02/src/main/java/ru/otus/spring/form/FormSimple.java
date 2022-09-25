package ru.otus.spring.form;

import org.springframework.stereotype.Component;
import ru.otus.spring.questionnaire.Questionnaire;

import java.util.List;

@Component
public class FormSimple implements Form {
    private List<Questionnaire> questionnaires;
    private List<String> columnNames;

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public void printForm() {
        for (int i = 0; i < getQuestionnaires().size(); i++) {
            System.out.println("Number question: " + getQuestionnaires().get(i).getId());
            System.out.println("Question: " + getQuestionnaires().get(i).getQuestion());
            List<String> responses = getQuestionnaires().get(i).getResponses();
            for (int j = 0; j < responses.size(); j++) {
                System.out.println(j + 1 + ": " + responses.get(j));
            }
        }
    }

    @Override
    public String toString() {
        return "FormSimple{" +
                "questionnaire=" + questionnaires +
                ", columnNames=" + columnNames +
//                ", person=" + person +
                '}';
    }
}
