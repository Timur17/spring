package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentService {
    long count();

    Comment insert(String comment, long bookId);

    void deleteById(long id);

    List<Comment> getAllByBookId(long id);
}
