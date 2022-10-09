package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

    @Value("${greeting:Hello}")
    private String greeting;
    private final PersonDao dao;

    @Autowired
    public PersonServiceImpl(@Qualifier("personDaoSimple") PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        System.out.println(greeting);
        return dao.findByName(name);
    }
}
