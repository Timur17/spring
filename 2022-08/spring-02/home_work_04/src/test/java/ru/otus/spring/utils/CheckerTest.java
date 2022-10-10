package ru.otus.spring.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {Checker.class})
class CheckerTest {

    @Autowired
    Checker checker;

    @Test
    public void isStringPositiveDigitsTest(){
        assertTrue(checker.isStringPositiveDigits("0"));
        assertTrue(checker.isStringPositiveDigits("9"));
        assertTrue(checker.isStringPositiveDigits("99999999999999"));
        assertFalse(checker.isStringPositiveDigits("-1"));
        assertFalse(checker.isStringPositiveDigits("-99999999999999"));
        assertFalse(checker.isStringPositiveDigits("text"));
    }


}