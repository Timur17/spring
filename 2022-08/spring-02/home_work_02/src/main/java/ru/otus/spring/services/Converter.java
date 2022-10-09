package ru.otus.spring.spring.services;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Converter {

    public List<String> convertStringToList(String str, String splitter) {
        return Arrays.stream(str.split(splitter)).collect(Collectors.toList());
    }
}
