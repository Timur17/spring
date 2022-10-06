package ru.otus.spring.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.spring.domain.Person;

@Component
public class PersonDaoSimple implements PersonDao {

    public Person findByName(String name) {
        return new Person(name, 18);
    }
}
