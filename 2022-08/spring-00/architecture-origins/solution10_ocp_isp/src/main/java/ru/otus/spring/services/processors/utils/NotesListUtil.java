package ru.otus.spring.services.processors.utils;

import ru.otus.spring.exceptions.NoteIndexOutOfBoundsException;

public class NotesListUtil {
    public static void checkNoteNumber(int noteNumber, int notesCount) {
        if (noteNumber <= 0 || noteNumber > notesCount) {
            throw new NoteIndexOutOfBoundsException("Given number of note is out of range");
        }
    }
}
