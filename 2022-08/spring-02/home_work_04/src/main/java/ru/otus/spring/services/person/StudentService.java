package ru.otus.spring.services.person;


import ru.otus.spring.configs.AppProps;
import ru.otus.spring.domain.Person;
import ru.otus.spring.form.Form;
import ru.otus.spring.questionnaire.Questionnaire;
import ru.otus.spring.services.ConsoleIOService;

import java.util.List;

public class StudentService implements PersonService {
    private final Person person;
    private final Form form;
    private final ConsoleIOService consoleIOService;
    private final AppProps props;


    public StudentService(Form form, ConsoleIOService consoleIOService, Person person, AppProps props) {
        this.form = form;
        this.consoleIOService = consoleIOService;
        this.person = person;
        this.props = props;
    }

    public void askName() {
        person.setFirstName(consoleIOService.readStringWithPrompt(props.getMessages().getMsgYourFirstName()));
        person.setLastName(consoleIOService.readStringWithPrompt(props.getMessages().getMsgYourLastName()));
    }

    public void askQuestion() {
        for (int i = 0; i < form.getQuestionnaires().size(); i++) {
            Questionnaire questionnaire = form.getQuestionnaires().get(i);
            consoleIOService.outputString(props.getMessages().getMsgQuestion() + " " + questionnaire.getId() + ": " + questionnaire.getQuestion());
            List<String> responses = questionnaire.getResponses();
            printResponses(responses);
            int numberResp = validateInput(responses);
            String chosenResponse = responses.get(numberResp - 1);
            if (chosenResponse.equals(props.getMessages().getOwnResponse())) {
                consoleIOService.outputString(props.getMessages().getMsgWrite() + " " + props.getMessages().getOwnResponse().toLowerCase());
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
                consoleIOService.outputString(props.getMessages().getMsgEnterCorrectNumber());
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
            consoleIOService.outputString(props.getMessages().getMsgChooseAnswer() + " 1 - " + responses.size());
            numberResp = parseNumber();
        }
        return numberResp;
    }

    public void printResult() {
        consoleIOService.outputString(props.getMessages().getMsgStudent() + ": " + person.getFirstName() + " " + person.getLastName() + "\n");
        for (int i = 0; i < form.getQuestionnaires().size(); i++) {
            consoleIOService.outputString(props.getMessages().getMsgQuestion() + " " + form.getQuestionnaires().get(i).getId() + ": " + form.getQuestionnaires().get(i).getQuestion());
            consoleIOService.outputString(props.getMessages().getMsgAnswer() +  " : " +
                    form.getQuestionnaires().get(i).getSelectedAnswer() + "\n");
        }
    }

}
