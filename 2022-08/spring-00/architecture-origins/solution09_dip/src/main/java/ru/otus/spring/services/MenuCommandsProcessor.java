package ru.otus.spring.services;

public interface MenuCommandsProcessor {
    void showAllNotes();

    void addNewNote();

    void updateNote();

    void deleteNote();

    void stopApplication();
}
