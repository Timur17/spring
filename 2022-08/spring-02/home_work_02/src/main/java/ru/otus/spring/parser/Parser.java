package ru.otus.spring.parser;


import ru.otus.spring.form.Form;

public interface Parser {
    public Form parseFile();
    public Form getForm();

}
