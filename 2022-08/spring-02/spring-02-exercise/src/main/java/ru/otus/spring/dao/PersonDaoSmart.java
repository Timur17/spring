package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Person;

@Component
public class PersonDaoSmart implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}
