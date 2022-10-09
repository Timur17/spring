package ru.otus.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Converter.class})
class ConverterTest {
    @Autowired
    Converter converter;

    @Test
    public void convertStringToListTest() {
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