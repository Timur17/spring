package ru.otus.spring.services.person;

import ru.otus.spring.domain.Person;
import ru.otus.spring.form.Form;
import ru.otus.spring.questionnaire.Questionnaire;
import ru.otus.spring.services.ConsoleIOService;

import java.util.List;

public class StudentService implements PersonService {
    private final String OWN_RESPONSE = "Own response";
    private Person person;
    private Form form;
    private final ConsoleIOService consoleIOService;

    public StudentService(Form form, ConsoleIOService consoleIOService, Person person) {
        this.form = form;
        this.consoleIOService = consoleIOService;
        this.person = person;
    }

    public void askName() {
        person.setFirstName(consoleIOService.readStringWithPrompt("Your first name:"));
        person.setLastName(consoleIOService.readStringWithPrompt("Your last name:"));
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
        consoleIOService.outputString("Student: " + person.getFirstName() + " " + person.getLastName() + "\n");
        for (int i = 0; i < form.getQuestionnaires().size(); i++) {
            consoleIOService.outputString("Question " + form.getQuestionnaires().get(i).getId() + ": " + form.getQuestionnaires().get(i).getQuestion());
            consoleIOService.outputString("Student answer: " + form.getQuestionnaires().get(i).getSelectedAnswer() + "\n");
        }
    }
}
