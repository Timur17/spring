package ru.otus.spring.service;


import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    void count();

    void insert(String title, String author, String genre);

    void updateById(String title, int id);

    void deleteById(int id);

    void showAll();

    void showById(int id);
}
