package ru.otus.services.processors;

import ru.otus.spring.services.ApplicationStopService;
import ru.otus.spring.services.menu.MenuOption;

public class StopApplicationSingleCommandProcessor implements MenuSingleCommandProcessor {
    private final MenuOption processedCommandOption;
    private final ApplicationStopService applicationStopService;

    public StopApplicationSingleCommandProcessor(ApplicationStopService applicationStopService,
                                                 MenuOption processedCommandOption) {
        this.applicationStopService = applicationStopService;
        this.processedCommandOption = processedCommandOption;
    }

    @Override
    public void processCommand() {
        applicationStopService.stopApplication();
    }

    @Override
    public MenuOption getProcessedCommandOption() {
        return processedCommandOption;
    }
}
