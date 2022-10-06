package ru.otus.spring.services.processors;

import ru.otus.spring.model.Note;
import ru.otus.spring.services.menu.MenuOption;
import ru.otus.spring.services.NotesService;
import ru.otus.spring.services.processors.utils.NotesListUtil;

public class UpdateNoteSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final InputService inputService;
    private final NotesService notesService;

    public UpdateNoteSingleCommandProcessor(InputService inputService, NotesService notesService,
                                            MenuOption processedCommandOption) {
        this.inputService = inputService;
        this.notesService = notesService;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        var notes = notesService.getAll();

        var updatedNoteNumber = inputService.readIntWithPrompt("Введите номер изменяемой заметки...");
        NotesListUtil.checkNoteNumber(updatedNoteNumber, notes.size());

        var noteText = inputService.readStringWithPrompt("Введите текст заметки...");

        var updatedNote = notes.get(updatedNoteNumber - 1);
        notesService.save(Note.of(updatedNote.getId(), noteText));    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }


}
