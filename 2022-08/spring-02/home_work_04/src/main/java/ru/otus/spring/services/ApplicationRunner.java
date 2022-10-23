package ru.otus.spring.services;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Person;
import ru.otus.spring.form.FormSimple;
import ru.otus.spring.parser.Parser;
import ru.otus.spring.services.person.StudentService;

@ShellComponent
@RequiredArgsConstructor
@Component
public class ApplicationRunner {
    private final Parser parser;
    private final ConsoleIOService consoleIOService;
    private final LocalQuestionnaireFile localQuestionnaireFile;
    private final StudentService studentService;

    @ShellMethod(value = "Start commands", key = {"start", "старт", "run", "begin", "начать"})
    public void run() {
        localQuestionnaireFile.init();

        FormSimple form = new FormSimple();
        parser.parseFile(form);

        Person person = new Person();
        studentService.askName(person);
        studentService.askQuestion(form);
        studentService.printResult(form, person);
    }

    @ShellMethod(value = "Exit commands", key = {"exit", "выйти"})
    public void exit() {
        System.exit(0);
    }
}
