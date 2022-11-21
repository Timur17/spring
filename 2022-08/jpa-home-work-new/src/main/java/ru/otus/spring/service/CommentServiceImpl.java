package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepositoryJpa;
import ru.otus.spring.service.ioservice.ConsoleIOService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepositoryJpa commentRepositoryJpa;
    private final ConsoleIOService consoleIOService;

    public CommentServiceImpl(CommentRepositoryJpa commentRepositoryJpa, ConsoleIOService consoleIOService) {
        this.commentRepositoryJpa = commentRepositoryJpa;
        this.consoleIOService = consoleIOService;
    }


    @Transactional(readOnly = true)
    @Override
    public void count() {
        long count = commentRepositoryJpa.count();
        consoleIOService.outputString("Amount comments: " + count);
    }

    @Transactional
    @Override
    public void insert(String comment, long bookId) {
        commentRepositoryJpa.insert(new Comment(comment, bookId));
    }

    @Transactional
    @Override
    public void updateById(String comment, long id) {
        commentRepositoryJpa.updateById(new Comment(comment), id);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepositoryJpa.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllByBookId(long bookId) {
        List<Comment> comments = commentRepositoryJpa.getAllByBookId(bookId);
        consoleIOService.outputString("Comments with book id : " + bookId + " is " + comments);
    }
}
