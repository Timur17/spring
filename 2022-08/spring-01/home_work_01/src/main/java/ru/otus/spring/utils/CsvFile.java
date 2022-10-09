package ru.otus.spring.spring.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvFile {

    public void createCsvFile(String path) {

        try (PrintWriter writer = new PrintWriter(new File(path))) {

            StringBuilder sb = new StringBuilder();
            sb.append("id");
            sb.append(',');
            sb.append("question");
            sb.append(',');
            sb.append("response_1");
            sb.append(',');
            sb.append("response_2");
            sb.append(',');
            sb.append("response_3");
            sb.append(',');
            sb.append("response_4");
            sb.append('\n');

            sb.append("1");
            sb.append(',');
            sb.append("Shortly about yourself");
            sb.append(',');
            sb.append("Own response");
            sb.append('\n');

            sb.append("2");
            sb.append(',');
            sb.append("What is your most lovely programming language?");
            sb.append(',');
            sb.append("java");
            sb.append(',');
            sb.append("python");
            sb.append(',');
            sb.append("C");
            sb.append(',');
            sb.append("Own response");
            sb.append('\n');

            sb.append("3");
            sb.append(',');
            sb.append("What is your teacher name?");
            sb.append(',');
            sb.append("James Arthur Gosling");
            sb.append(',');
            sb.append("Stack Overflow");
            sb.append(',');
            sb.append("Joshua Bloch");
            sb.append(',');
            sb.append("I don't need teacher");
            sb.append('\n');

            sb.append("4");
            sb.append(',');
            sb.append("How long are you codding?");
            sb.append(',');
            sb.append("Less one year");
            sb.append(',');
            sb.append("Between one and two years");
            sb.append(',');
            sb.append("More two year");
            sb.append(',');
            sb.append("Forever");
            sb.append('\n');

            sb.append("5");
            sb.append(',');
            sb.append("Expected salary?");
            sb.append(',');
            sb.append("100 000 - 200 000");
            sb.append(',');
            sb.append("200 000 - 300 000r");
            sb.append(',');
            sb.append("300 000 - infinity");
            sb.append(',');
            sb.append("I am ready to work for food");
            sb.append('\n');

            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
