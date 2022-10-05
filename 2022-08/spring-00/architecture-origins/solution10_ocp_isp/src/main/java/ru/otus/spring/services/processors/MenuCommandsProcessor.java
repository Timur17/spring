package ru.otus.spring.services.processors;

import ru.otus.spring.services.menu.MenuOption;

public interface MenuCommandsProcessor {
    void processMenuCommand(MenuOption selectedMenuOption);
}
