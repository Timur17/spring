package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Form;
import ru.otus.spring.parser.ParserCsv;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Form form = context.getBean(Form.class);
        ParserCsv parser = context.getBean(ParserCsv.class);
        parser.parseFile();
        parser.printForm();
    }
}
