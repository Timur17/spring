package ru.otus.spring.service;


import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    void count();

    void insert(String title, String author, String genre);

    void updateById(String title, long id);

    void deleteById(long id);

    void showAll();

    Book getById(long id);

    void showById(long id);
}
