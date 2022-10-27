package ru.otus.spring.service;


import ru.otus.spring.domain.BookAuthor;

import java.util.List;

public interface AuthorService {

    int count();

    int insert(String author);

    void updateById(String Author, int id);

    void deleteById(int id);

    List<BookAuthor> getAll();

    BookAuthor getById(int id);

    BookAuthor getByIdAllHisBook(int id);
}
