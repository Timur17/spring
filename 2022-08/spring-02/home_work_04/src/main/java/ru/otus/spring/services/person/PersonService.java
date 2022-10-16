package ru.otus.spring.services.person;

import ru.otus.spring.domain.Person;
import ru.otus.spring.form.Form;

public interface PersonService {

    public void askName(Person person);

    public void askQuestion(Form form);

    public void printResult(Form form, Person person);

}
