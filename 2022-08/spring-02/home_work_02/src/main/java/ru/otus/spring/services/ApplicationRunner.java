package ru.otus.spring.services;

import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Student;
import ru.otus.spring.form.Form;
import ru.otus.spring.parser.Parser;


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

        Person student = new Student(form, consoleIOService);
        student.askName();
        student.askQuestion();
        student.printResult();
    }

}
