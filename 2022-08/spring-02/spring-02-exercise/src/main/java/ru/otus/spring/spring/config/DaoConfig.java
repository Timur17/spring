package ru.otus.spring.spring.config;

import ru.otus.spring.spring.dao.PersonDao;
import ru.otus.spring.spring.dao.PersonDaoSimple;

public class DaoConfig {

    public PersonDao personDao() {
        return new PersonDaoSimple();
    }
}
