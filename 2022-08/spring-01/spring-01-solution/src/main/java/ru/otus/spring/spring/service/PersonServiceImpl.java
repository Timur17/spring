package ru.otus.spring.spring.service;

import ru.otus.spring.spring.dao.PersonDao;
import ru.otus.spring.spring.domain.Person;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }
}
