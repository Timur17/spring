package ru.otus.spring.services;

import ru.otus.spring.model.Note;

public class NoteConverter {
    public String convertNoteToString(int noteNumber, Note note) {
        return noteNumber + " | " + note.getCreationTime() + " | " + note.getText();
    }
}
