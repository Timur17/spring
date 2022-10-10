package ru.otus.spring.parser;


import org.springframework.stereotype.Component;
import ru.otus.spring.configs.AppProps;
import ru.otus.spring.form.Form;
import ru.otus.spring.questionnaire.Questionnaire;
import ru.otus.spring.questionnaire.QuestionnaireService;
import ru.otus.spring.services.Converter;
import ru.otus.spring.utils.Checker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParserCsv implements Parser {
    private final Converter converter;
    private final AppProps appProps;
    private final Checker checker;

    public ParserCsv(Checker checker, Converter converter, AppProps appProps) {
        this.converter = converter;
        this.appProps = appProps;
        this.checker = checker;
    }

    @Override
    public void parseFile(Form form) {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        int countLines = 0;
        try (InputStream inputStream = Parser.class.getClassLoader().getResourceAsStream(appProps.getFile());
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = converter.convertStringToList(line, ",");
                if (countLines == 0) {
                    form.setColumnNames(values);
                } else {
                    questionnaireList.add(parseLine(values));
                }
                countLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        form.setQuestionnaires(questionnaireList);
    }


    private QuestionnaireService parseLine(List<String> values) {
        int id = parseId(values);
        QuestionnaireService questionnaireService = new QuestionnaireService(id);
        questionnaireService.setQuestion(values.get(1));
        for (int i = 2; i < values.size(); i++) {
            questionnaireService.addResponse(values.get(i));
        }
        return questionnaireService;
    }

    private int parseId(List<String> values) {
        if (!checker.isStringPositiveDigits(values.get(0))) {
            System.out.println(String.format(appProps.getMessages().getMsgIdIsNotInt(), values));
            return -1;
        }
        return Integer.parseInt(values.get(0));
    }
}
