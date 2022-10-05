package ru.otus.spring.services;

import ru.otus.spring.model.Note;

public interface NoteConverter {
    String convertNoteToString(int noteNumber, Note note);
}
