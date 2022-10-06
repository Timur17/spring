package ru.otus.spring.spring.services;

import ru.otus.spring.spring.domain.Person;
import ru.otus.spring.spring.services.person.PersonService;
import ru.otus.spring.spring.services.person.StudentService;
import ru.otus.spring.spring.form.Form;
import ru.otus.spring.spring.parser.Parser;


public class ApplicationRunner {
    private Parser parser;
    private ConsoleIOService consoleIOService;

    public ApplicationRunner(Parser parser, ConsoleIOService consoleIOService) {
        this.parser = parser;
        this.consoleIOService = consoleIOService;
    }

    public void run() {
        parser.parseFile();
        Form form = parser.getForm();

        PersonService student = new StudentService(form, consoleIOService, new Person());
        student.askName();
        student.askQuestion();
        student.printResult();
    }

}
