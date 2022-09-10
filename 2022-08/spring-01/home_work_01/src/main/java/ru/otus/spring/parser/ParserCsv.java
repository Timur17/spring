package ru.otus.spring.parser;

import ru.otus.spring.domain.Form;
import ru.otus.spring.domain.Questionnaire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static ru.otus.spring.utils.Helpers.convertStringToList;
import static ru.otus.spring.utils.Helpers.isStringDigits;

public class ParserCsv implements Parser {
    private String fileWithData;
    private final Form form;

    public ParserCsv(Form form) {
        this.form = form;
    }

    public void setFileWithData(String fileWithData) {
        this.fileWithData = fileWithData;
    }

    @Override
    public Form parseFile() {
        List<Questionnaire> questionnaireList = new ArrayList<>();
        int countLines = 0;

        try (InputStream inputStream = Parser.class.getClassLoader().getResourceAsStream(fileWithData);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = convertStringToList(line, ",");
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
        form.setQuestionnaire(questionnaireList);
        return form;
    }


    public Questionnaire parseLine(List<String> values) {
        int id = parseId(values);
        Questionnaire questionnaire = new Questionnaire(id);
        questionnaire.setQuestion(values.get(1));
        for (int i = 2; i < values.size(); i++) {
            questionnaire.addResponse(values.get(i));
        }
        return questionnaire;
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

    public void printForm() {
        for (int i = 0; i < form.getQuestionnaire().size(); i++) {
            System.out.println("Number question: " + form.getQuestionnaire().get(i).getId());
            System.out.println("Question: " + form.getQuestionnaire().get(i).getQuestion());
            List<String> responses = form.getQuestionnaire().get(i).getResponses();
            for (int j = 0; j < responses.size(); j++) {
                System.out.println("" + 1 + ": " + responses.get(j));
            }
        }
    }

}
