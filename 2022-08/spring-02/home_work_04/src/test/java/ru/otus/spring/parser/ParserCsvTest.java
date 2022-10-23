package ru.otus.spring.parser;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.configs.AppProps;
import ru.otus.spring.configs.Messages;
import ru.otus.spring.form.FormSimple;
import ru.otus.spring.questionnaire.Questionnaire;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@SpringBootTest
class ParserCsvTest {

    @Autowired
    private ParserCsv parser;

    @MockBean
    private AppProps appProps;

    @MockBean
    private Messages messages;

    @Test
    public void parseFileTest() {
        given(appProps.getFile()).willReturn("questionEnTest.csv");

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

    @Test
    public void parseFileIncorrectIdTest() {
        given(messages.getMsgIdIsNotInt()).willReturn("ERROR: id is not int so id will be set as -1. Line:");
        given(appProps.getFile()).willReturn("questionIncorrectId.csv");
        given(appProps.getMessages()).willReturn(messages);


        System.out.println(appProps.getFile());
        FormSimple form = new FormSimple();
        parser.parseFile(form);

        Questionnaire studentQuestionnaire = form.getQuestionnaires().get(0);
        assertEquals(studentQuestionnaire.getId(), -1, "Invalid questionnaire: " + studentQuestionnaire);
    }

    @Test
    public void parseFileRuTest() {
        given(appProps.getFile()).willReturn("questionRuTest.csv");
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