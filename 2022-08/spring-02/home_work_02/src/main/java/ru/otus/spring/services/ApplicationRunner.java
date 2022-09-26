package ru.otus.spring.services;

import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Student;
import ru.otus.spring.form.Form;
import ru.otus.spring.parser.Parser;


public class ApplicationRunner {
    private Parser parser;

    public ApplicationRunner(Parser parser) {
        this.parser = parser;
    }

    public void run() {
        parser.parseFile();
        Form form = parser.getForm();

        Person student = new Student(form);
        student.askName();
        student.askQuestion();
        student.printResult();
    }

}
