package ru.otus.spring.spring.config;

import ru.otus.spring.spring.dao.PersonDao;
import ru.otus.spring.spring.service.PersonService;
import ru.otus.spring.spring.service.PersonServiceImpl;

public class ServicesConfig {

    public PersonService personService(PersonDao dao) {
        return new PersonServiceImpl(dao);
    }
}
