package ru.otus.spring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.parser.ParserCsv;
import ru.otus.spring.questionnaire.StudentQuestionnaire;
import ru.otus.spring.services.Converter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ComponentScan
class FormTest {
    static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(Main.class);
    }

    @Test
    public void fileWithDataExistTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        assertTrue("questionTest.csv".equals(parser.getFileWithData()));
    }

    @Test
    public void getIdFromLinePositiveTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        Converter converter = context.getBean(Converter.class);
        String line = "2,What is your most lovely programming language?,java,python,c,Own response";
        List<String> values = converter.convertStringToList(line, ",");
        int id = parser.parseId(values);
        assertEquals(2, id);
    }

    @Test
    public void getIdFromLineNegativeTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        Converter converter = context.getBean(Converter.class);
        String line = "notInt,What is your most lovely programming language?,java,python,c,Own response";
        List<String> values = converter.convertStringToList(line, ",");
        int id = parser.parseId(values);
        assertEquals(-1, id);
    }

    @Test
    public void parseLineTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        Converter converter = context.getBean(Converter.class);
        String line = "2,What is your most lovely programming language?,java,python,c,Own response";
        List<String> values = converter.convertStringToList(line, ",");
        StudentQuestionnaire studentQuestionnaire = parser.parseLine(values);
        assertEquals(2, studentQuestionnaire.getId());
        assertTrue("What is your most lovely programming language?".equals(studentQuestionnaire.getQuestion()));
        assertEquals(4, studentQuestionnaire.getResponses().size());
    }


}