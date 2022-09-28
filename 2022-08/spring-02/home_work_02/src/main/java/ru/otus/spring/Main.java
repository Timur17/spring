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
        ParserCsv parser = context.getBean(ParserCsv.class);
        ConsoleIOService consoleIOService = context.getBean(ConsoleIOService.class);
        new ApplicationRunner(parser, consoleIOService).run();
    }
}
