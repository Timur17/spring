package ru.otus.spring.parser;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.form.Form;
import ru.otus.spring.questionnaire.Questionnaire;
import ru.otus.spring.questionnaire.StudentQuestionnaire;
import ru.otus.spring.services.Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static ru.otus.spring.utils.Helpers.isStringDigits;

@Component
public class ParserCsv implements Parser {
    @Value("${srs.file}")
    private String fileWithData;
    private final Form form;
    private final Converter converter;

    public ParserCsv(Form form, Converter converter) {
        this.form = form;
        this.converter = converter;
    }

    @Override
    public Form parseFile() {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        int countLines = 0;

        try (InputStream inputStream = Parser.class.getClassLoader().getResourceAsStream(fileWithData);
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
        return form;
    }


    public StudentQuestionnaire parseLine(List<String> values) {
        int id = parseId(values);
        StudentQuestionnaire studentQuestionnaire = new StudentQuestionnaire(id);
        studentQuestionnaire.setQuestion(values.get(1));
        for (int i = 2; i < values.size(); i++) {
            studentQuestionnaire.addResponse(values.get(i));
        }
        return studentQuestionnaire;
    }

    public int parseId(List<String> values) {
        if (!isStringDigits(values.get(0))) {
            System.out.println(String.format("ERROR: id is not int so id will be set as -1. Line: ", values));
            return -1;
        }
        return Integer.parseInt(values.get(0));
    }

    public String getFileWithData() {
        return fileWithData;
    }


    public Form getForm() {
        return form;
    }
}
