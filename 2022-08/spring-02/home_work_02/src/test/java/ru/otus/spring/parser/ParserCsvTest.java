package ru.otus.spring.spring.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.spring.Main;
import ru.otus.spring.spring.form.Form;
import ru.otus.spring.spring.questionnaire.Questionnaire;
import ru.otus.spring.spring.utils.CsvFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ParserCsvTest {
    static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
//        String path = "src/test/resources/";
//        File file = new File(path);
//        if (!file.exists()) {
//            new CsvFile().createCsvFile(path);
//        }
//        assertTrue(file.exists(), "File is not exist: " + path);
//        context = new AnnotationConfigApplicationContext(Main.class);
    }

    @Test
    public void parseFileTest() {
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
            assertTrue(studentQuestionnaire.getQuestion().length() > 0);
            assertTrue(studentQuestionnaire.getResponses().size() > 0);
        }
    }
}