package ru.otus.spring;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.parser.ParserCsv;
import ru.otus.spring.services.ApplicationRunner;
import ru.otus.spring.services.ConsoleIOService;

@PropertySource("classpath:app.properties")
@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ApplicationRunner applicationRunner = context.getBean(ApplicationRunner.class);
        applicationRunner.run();
    }
}
