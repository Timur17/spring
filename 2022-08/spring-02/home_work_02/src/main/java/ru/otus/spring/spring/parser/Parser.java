package ru.otus.spring.spring.parser;


import ru.otus.spring.spring.form.Form;

public interface Parser {
    public Form parseFile();
    public Form getForm();

}
