package ru.otus.spring.spring.utils;

import java.util.regex.Pattern;

public final class Helpers {

    public static boolean isStringDigits(String string) {
        return Pattern.matches("[0-9]+", string);
    }

}
