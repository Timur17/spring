package ru.otus.springdata.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.otus.springdata.domain.Person;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @EntityGraph(attributePaths = "email")
    @Override
    List<Person> findAll();

    Optional<Person> findByName(String name);

    Optional<Person> findByEmailAddress(String address);
}
