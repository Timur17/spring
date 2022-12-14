package ru.otus.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import ru.otus.spring.domain.Email;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.EmailRepository;
import ru.otus.spring.repostory.PersonRepository;

import javax.annotation.PostConstruct;

@EnableMapRepositories
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PersonRepository repository;

    @Autowired
    private EmailRepository emailRepository;

    @PostConstruct
    public void init() {
        repository.save(new Person(1, "Pushkin"));

        System.out.println( "Test::: " + repository.findAll());

        emailRepository.save(new Email(1, "pushkin@gmail.com"));
        System.out.println( "Test::: " + emailRepository.findAll());

    }
}
