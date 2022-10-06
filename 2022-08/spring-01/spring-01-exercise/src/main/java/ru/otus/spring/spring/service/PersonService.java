package ru.otus.spring.spring.service;

import ru.otus.spring.spring.domain.Person;

public interface PersonService {

    Person getByName(String name);
}
