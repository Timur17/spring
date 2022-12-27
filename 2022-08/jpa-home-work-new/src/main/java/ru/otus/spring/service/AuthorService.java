package ru.otus.spring.service;


import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    long count();

    Author insert(String author);

    Author updateById(String Author, String id);

    void deleteById(String id);

    List<Author> getAll();

    Optional<Author> getById(String id);
}
