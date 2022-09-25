package ru.otus.spring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.form.Form;
import ru.otus.spring.questionnaire.Questionnaire;
import ru.otus.spring.questionnaire.StudentQuestionnaire;
import ru.otus.spring.parser.ParserCsv;
import ru.otus.spring.utils.CsvFile;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.otus.spring.utils.Helpers.convertStringToList;

@ComponentScan
class FormTest {
    static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        String path = "src/main/resources";
        File file = new File(path);
        if (!file.exists()) {
            new CsvFile().createCsvFile(path);
        }
        assertTrue(file.exists(), "File is not exist: " + path);
        context = new AnnotationConfigApplicationContext(Main.class);
    }

    @Test
    public void createCsvFile() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        Form form = parser.parseFile();

        assertNotNull(form.getColumnNames());
        assertEquals(form.getColumnNames().size(), 6);
        assertNotNull(form.getQuestionnaires());
        assertEquals(form.getQuestionnaires().size(), 5);
        for (int i = 0; i < form.getQuestionnaires().size(); i++) {
            Questionnaire studentQuestionnaire = form.getQuestionnaires().get(i);
            assertEquals(studentQuestionnaire.getId(), i + 1, "Invalid questionnaire: " + studentQuestionnaire);
            assertNotNull(studentQuestionnaire.getQuestion(), "Invalid questionnaire: " + studentQuestionnaire);
            assertTrue(studentQuestionnaire.getResponses().size() > 0);
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
        List<String> values = convertStringToList(line, ",");
        int id = parser.parseId(values);
        assertEquals(2, id);
    }

    @Test
    public void getIdFromLineNegativeTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        String line = "notInt,What is your most lovely programming language?,java,python,c,Own response";
        List<String> values = convertStringToList(line, ",");
        int id = parser.parseId(values);
        assertEquals(-1, id);
    }

    @Test
    public void parseLineTest() {
        ParserCsv parser = context.getBean(ParserCsv.class);
        String line = "2,What is your most lovely programming language?,java,python,c,Own response";
        List<String> values = convertStringToList(line, ",");
        StudentQuestionnaire studentQuestionnaire = parser.parseLine(values);
        assertEquals(2, studentQuestionnaire.getId());
        assertTrue("What is your most lovely programming language?".equals(studentQuestionnaire.getQuestion()));
        assertEquals(4, studentQuestionnaire.getResponses().size());
    }


}