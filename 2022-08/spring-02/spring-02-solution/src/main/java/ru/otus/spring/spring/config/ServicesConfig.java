package ru.otus.spring.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.spring.dao.PersonDao;
import ru.otus.spring.spring.service.PersonService;
import ru.otus.spring.spring.service.PersonServiceImpl;

@Configuration
public class ServicesConfig {

    @Bean
    public PersonService personService(PersonDao dao) {
        return new PersonServiceImpl(dao);
    }
}
