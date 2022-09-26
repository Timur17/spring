package ru.otus.spring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.form.Form;
import ru.otus.spring.parser.ParserCsv;
import ru.otus.spring.questionnaire.Questionnaire;
import ru.otus.spring.utils.CsvFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan
class IntegrationFormTest {
    static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        String path = "src/test/resources/questionTest.csv";
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
}