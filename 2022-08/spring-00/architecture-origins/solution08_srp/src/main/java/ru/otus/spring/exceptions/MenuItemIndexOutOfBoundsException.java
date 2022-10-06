package ru.otus.spring.exceptions;

public class MenuItemIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public MenuItemIndexOutOfBoundsException(String s) {
        super(s);
    }
}
