package ru.otus.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.springdata.domain.Email;
import ru.otus.springdata.domain.Person;
import ru.otus.springdata.repository.EmailRepository;
import ru.otus.springdata.repository.PersonRepository;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        EmailRepository emailRepository = context.getBean(EmailRepository.class);

        Email email = new Email("sashka@gmail.com");
        emailRepository.save(email);


         personRepository.save(new Person("Александр Сергеевич Пушкин", email));
//         personRepository.save(new Person("Михаил Юрьевич Лермонтов", new Email("sashka@gmail.com")));
//         personRepository.save(new Person("Михаил Сергеевич Горбачев", new Email("gorbatiy@gmail.com")));

        System.out.println(personRepository.findAll());
        System.out.println(emailRepository.findAll());
        System.out.println(personRepository.findByEmailAddress("sashka@gmail.com"));

        System.out.println(emailRepository.findByAddress("sashka@gmail.com"));

        emailRepository.updateEmailById(1L, "sashka_17@gmail.com");

        System.out.println(emailRepository.findAll());


    }


}
