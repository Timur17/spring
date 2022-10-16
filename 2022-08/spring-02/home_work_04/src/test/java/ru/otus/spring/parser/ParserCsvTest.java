package ru.otus.spring.parser;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.Main;
import ru.otus.spring.configs.AppProps;
import ru.otus.spring.configs.Messages;
import ru.otus.spring.form.Form;
import ru.otus.spring.form.FormSimple;
import ru.otus.spring.questionnaire.Questionnaire;
import ru.otus.spring.services.ConsoleIOService;
import ru.otus.spring.services.Converter;
import ru.otus.spring.services.LocalQuestionnaireFile;
import ru.otus.spring.services.person.StudentService;
import ru.otus.spring.utils.Checker;
import ru.otus.spring.utils.CsvFile;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


@EnableConfigurationProperties(AppProps.class)
@SpringBootTest(classes = {Messages.class, LocalQuestionnaireFile.class,
        MessageSource.class, ParserCsv.class, ConsoleIOService.class,
         Checker.class, Converter.class})
//@SpringBootTest
class ParserCsvTest {
    @Autowired
    private ParserCsv parser;
    @Autowired
    private ConsoleIOService consoleIOService;
    @Autowired
    private LocalQuestionnaireFile localQuestionnaireFile;

    @Test
    public void parseFileTest() {
        localQuestionnaireFile.init();
        FormSimple form = new FormSimple();
        parser.parseFile(form);

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