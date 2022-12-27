package ru.otus.spring.service;

import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentService {
    long count();

    Comment insert(String comment, String bookId);

    void deleteById(String id);

    List<Comment> getAllByBookId(String id);

}
