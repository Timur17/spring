package ru.otus.spring.services;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.configs.AppProps;
import ru.otus.spring.domain.Person;
import ru.otus.spring.form.Form;
import ru.otus.spring.parser.Parser;
import ru.otus.spring.services.person.PersonService;
import ru.otus.spring.services.person.StudentService;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private final Parser parser;
    private final ConsoleIOService consoleIOService;
    private final AppProps props;
    private final LocalQuestionnaireFile localQuestionnaireFile;

    public ApplicationRunner(Parser parser, ConsoleIOService consoleIOService,
                             AppProps props, LocalQuestionnaireFile localQuestionnaireFile) {
        this.parser = parser;
        this.consoleIOService = consoleIOService;
        this.props = props;
        this.localQuestionnaireFile = localQuestionnaireFile;
    }

    @Override
    public void run(String... args) throws Exception {
        localQuestionnaireFile.init();
        parser.parseFile();
        Form form = parser.getForm();

        PersonService student = new StudentService(form, consoleIOService, new Person(), props);
        student.askName();
        student.askQuestion();
        student.printResult();
    }
}
