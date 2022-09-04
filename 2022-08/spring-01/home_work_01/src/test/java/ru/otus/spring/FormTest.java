package ru.otus.spring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Form;
import ru.otus.spring.domain.Questionnaire;
import ru.otus.spring.parser.ParserCsv;
import ru.otus.spring.utils.CsvFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FormTest {
    @BeforeAll
    public static void setUp() {
        String path = "src/main/resources";
        File file = new File(path);
        if (!file.exists()) {
            new CsvFile().createCsvFile(path);
        }
        assertTrue(file.exists(), "File is not exist: " + path);
    }

    @Test
    public void createCsvFile() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
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
}