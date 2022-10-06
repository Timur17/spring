package ru.otus.spring.services.processors;

import ru.otus.spring.services.NotesService;
import ru.otus.spring.services.menu.MenuOption;
import ru.otus.spring.services.processors.utils.NotesListUtil;

public class DeleteNoteSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final InputService inputService;
    private final NotesService notesService;

    public DeleteNoteSingleCommandProcessor(InputService inputService, NotesService notesService,
                                            MenuOption processedCommandOption) {
        this.inputService = inputService;
        this.notesService = notesService;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        var notes = notesService.getAll();

        var deletedNoteNumber = inputService.readIntWithPrompt("Введите номер удаляемой заметки...");
        NotesListUtil.checkNoteNumber(deletedNoteNumber, notes.size());

        var updatedNote = notes.get(deletedNoteNumber - 1);
        notesService.remove(updatedNote.getId());
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }

}
