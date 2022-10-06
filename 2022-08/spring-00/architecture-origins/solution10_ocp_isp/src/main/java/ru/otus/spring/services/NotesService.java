package ru.otus.spring.services;

import ru.otus.spring.model.Note;

import java.util.List;

public interface NotesService {
    List<Note> getAll();

    void save(Note note);

    void remove(String id);
}
