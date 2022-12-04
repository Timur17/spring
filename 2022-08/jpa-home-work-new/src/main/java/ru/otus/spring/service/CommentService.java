package ru.otus.spring.service;

public interface CommentService {
    void count();

    void insert(String comment, long bookId);

    void deleteById(long id);

    void showAllByBookId(long id);
}
