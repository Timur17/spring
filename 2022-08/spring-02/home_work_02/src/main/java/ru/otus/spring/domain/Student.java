package ru.otus.spring.domain;

import ru.otus.spring.form.Form;
import ru.otus.spring.questionnaire.Questionnaire;
import ru.otus.spring.services.ConsoleIOService;

import java.util.List;

public class Student implements Person {
    private final String OWN_RESPONSE = "Own response";
    private String firstName;
    private String lastName;
    private Form form;
    private final ConsoleIOService consoleIOService;

    public Student(Form form, ConsoleIOService consoleIOService) {
        this.form = form;
        this.consoleIOService = consoleIOService;
    }

    public void askName() {
        firstName = consoleIOService.readStringWithPrompt("Your first name:");
        lastName = consoleIOService.readStringWithPrompt("Your last name:");
    }

    public void askQuestion() {
        for (int i = 0; i < form.getQuestionnaires().size(); i++) {
            Questionnaire questionnaire = form.getQuestionnaires().get(i);
            consoleIOService.outputString("Question " + questionnaire.getId() + ": " + questionnaire.getQuestion());
            List<String> responses = questionnaire.getResponses();
            printResponses(responses);
            int numberResp = validateInput(responses);
            String chosenResponse = responses.get(numberResp - 1);
            if (chosenResponse.equals(OWN_RESPONSE)) {
                consoleIOService.outputString("Write own answer.");
                questionnaire.setSelectedAnswer(consoleIOService.readString());
            } else
                questionnaire.setSelectedAnswer(chosenResponse);
        }
    }

    private void printResponses(List<String> responses) {
        for (int j = 0; j < responses.size(); j++) {
            consoleIOService.outputString(j + 1 + ": " + responses.get(j));
        }
    }

    private int parseNumber() {
        Integer numberResp = null;
        while (numberResp == null) {
            try {
                numberResp = consoleIOService.readInt();
            } catch (NumberFormatException e) {
                consoleIOService.outputString("Enter correct number.");
            }
        }
        return numberResp;
    }

    private int validateInput(List<String> responses) {
        if (responses.size() == 1)
            return 1;
        else return chooseAnswerNumber(responses);
    }

    private int chooseAnswerNumber(List<String> responses) {
        int numberResp = 0;
        while (numberResp < 1 || numberResp > responses.size()) {
            consoleIOService.outputString("Please choose answer from 1 to " + responses.size());
            numberResp = parseNumber();
        }
        return numberResp;
    }

    public void printResult() {
        consoleIOService.outputString("Student: " + firstName + " " + lastName + "\n");
        for (int i = 0; i < form.getQuestionnaires().size(); i++) {
            consoleIOService.outputString("Question " + form.getQuestionnaires().get(i).getId() + ": " + form.getQuestionnaires().get(i).getQuestion());
            consoleIOService.outputString("Student answer: " + form.getQuestionnaires().get(i).getSelectedAnswer() + "\n");
        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
