package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Student;
import ru.otus.spring.form.Form;
import ru.otus.spring.parser.ParserCsv;

@PropertySource("classpath:app.properties")
@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ParserCsv parser = context.getBean(ParserCsv.class);

        parser.parseFile();
        Form form = parser.getForm();

        Person student = new Student(form);
        student.askName();
        student.askQuestion();
        student.printResult();
    }
}
