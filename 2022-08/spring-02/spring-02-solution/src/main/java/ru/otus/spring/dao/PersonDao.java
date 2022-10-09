package ru.otus.spring.spring.dao;

import ru.otus.spring.spring.domain.Person;

public interface PersonDao {

    Person findByName(String name);
}
