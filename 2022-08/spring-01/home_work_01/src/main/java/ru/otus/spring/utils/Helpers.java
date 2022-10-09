package ru.otus.spring.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Helpers {

    public static boolean isStringDigits(String string) {
        return Pattern.matches("[0-9]+", string);
    }

    public static List<String> convertStringToList(String str, String splitter) {
        return Arrays.stream(str.split(splitter)).collect(Collectors.toList());
    }
}
