package ru.otus.spring.controller;


public interface BookService {

    void help();

    int count();

    void insert(String title, String author, String genre);

    void update(String title, String author, String genre, int id);

    void deleteById(int id);

    void getAll();

    void getById(int id);
}
