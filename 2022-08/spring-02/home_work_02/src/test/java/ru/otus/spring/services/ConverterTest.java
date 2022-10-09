package ru.otus.spring.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.Main;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConverterTest {
    static AnnotationConfigApplicationContext context;

    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(Main.class);
    }

    @Test
    public void convertStringToListTest() {
        Converter converter = context.getBean(Converter.class);
        String line = "2,What is your most lovely programming language?,java,python,c,Own response";
        List<String> values = converter.convertStringToList(line, ",");
        System.out.println(values);
        assertTrue(values.get(0).equals("2"));
        assertTrue(values.get(1).equals("What is your most lovely programming language?"));
        assertTrue(values.get(2).equals("java"));
        assertTrue(values.get(3).equals("python"));
        assertTrue(values.get(4).equals("c"));
        assertTrue(values.get(5).equals("Own response"));
    }
}