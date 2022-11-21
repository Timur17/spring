package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;

import java.util.Optional;

public interface CommentService {
    void count();

    void insert(String comment, long bookId);

    void updateById(String comment, long id);

    void deleteById(long id);

    void showAllByBookId(long id);
}
