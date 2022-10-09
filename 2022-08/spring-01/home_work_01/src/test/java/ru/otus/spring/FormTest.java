package ru.otus.spring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Form;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.parser.ParserCsv;
import ru.otus.spring.utils.CsvFile;
import ru.otus.spring.utils.Helpers;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FormTest {
    static ClassPathXmlApplicationContext context;

    @BeforeAll
    public static void setUp() {
        String path = "src/main/resources";
        File file = new File(path);
        if (!file.exists()) {
            new CsvFile().createCsvFile(path);
        }
        assertTrue(file.exists(), "File is not exist: " + path);
        context = new ClassPathXmlApplicationContext("spring-context.xml");

    }

    @Test
    public void createCsvFile() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        Form form = parser.parseFile();

        assertNotNull(form.getColumnNames());
        assertEquals(form.getColumnNames().size(), 6);
        assertNotNull(form.getQuestionnaire());
        assertEquals(form.getQuestionnaire().size(), 5);
        for (int i = 0; i < form.getQuestionnaire().size(); i++) {
            Questionnaire questionnaire = form.getQuestionnaire().get(i);
            assertEquals(questionnaire.getId(), i + 1, "Invalid questionnaire: " + questionnaire);
            assertNotNull(questionnaire.getQuestion(), "Invalid questionnaire: " + questionnaire);
            assertTrue(questionnaire.getResponses().size() > 0);
        }
    }

    @Test
    public void fileWithDataExistTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        assertTrue("question.csv".equals(parser.getFileWithData()));
    }

    @Test
    public void getIdFromLinePositiveTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        String line = "2,What is your most lovely programming language?,java,python,c,Own response";
        List<String> values = Helpers.convertStringToList(line, ",");
        int id = parser.parseId(values);
        assertEquals(2, id);
    }

    @Test
    public void getIdFromLineNegativeTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        String line = "notInt,What is your most lovely programming language?,java,python,c,Own response";
        List<String> values = Helpers.convertStringToList(line, ",");
        int id = parser.parseId(values);
        assertEquals(-1, id);
    }

    @Test
    public void parseLineTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        String line = "2,What is your most lovely programming language?,java,python,c,Own response";
        List<String> values = Helpers.convertStringToList(line, ",");
        Questionnaire questionnaire = parser.parseLine(values);
        assertEquals(2, questionnaire.getId());
        assertTrue("What is your most lovely programming language?".equals(questionnaire.getQuestion()));
        assertEquals(4, questionnaire.getResponses().size());
    }


}