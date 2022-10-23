package ru.otus.spring.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.configs.AppProps;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = {"application.locale=en"})
class LocalQuestionnaireFileEnTest {

    @Autowired
    private AppProps appProps;

    @Autowired
    private LocalQuestionnaireFile localQuestionnaireFile;


    @Test
    public void initTest() {
        localQuestionnaireFile.init();
        assertNotNull(appProps.getFile());
        assertNotNull(appProps.getLocale());
        assertNotNull(appProps.getMessages());
    }

}