package ru.otus.spring.services.processors;

public interface InputService {
    int readInt();

    int readIntWithPrompt(String prompt);

    String readStringWithPrompt(String prompt);
}
