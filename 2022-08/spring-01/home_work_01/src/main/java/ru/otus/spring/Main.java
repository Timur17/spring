package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Form;
import ru.otus.spring.parser.ParserCsv;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Form form = context.getBean(Form.class);
        ParserCsv parser = context.getBean(ParserCsv.class);
        parser.parseFile();
        print(form);
    }

    private static void print(Form form) {
        for (int i = 0; i < form.getQuestionnaire().size(); i++) {
            System.out.println("Number question: " + form.getQuestionnaire().get(i).getId());
            System.out.println("Question: " + form.getQuestionnaire().get(i).getQuestion());
            List<String> responses = form.getQuestionnaire().get(i).getResponses();
            for (int j = 0; j < responses.size(); j++) {
                System.out.println("" + 1 + ": " + responses.get(j));
            }
        }
    }


}
