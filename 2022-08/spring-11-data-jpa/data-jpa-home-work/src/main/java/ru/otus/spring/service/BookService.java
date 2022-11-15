package ru.otus.spring.service;


import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    int count();

    int insert(String title, String author, String genre);

    void updateById(String title, String author, String genre, int id);

    void deleteById(int id);

    List<Book> getAll();

    Book getById(int id);
}
