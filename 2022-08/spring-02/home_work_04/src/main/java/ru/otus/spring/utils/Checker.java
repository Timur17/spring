package ru.otus.spring.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public final class Checker {

    public boolean isStringPositiveDigits(String string) {
        return Pattern.matches("[0-9]+", string);
    }

}
