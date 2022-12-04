package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepositoryJpa;
import ru.otus.spring.service.ioservice.ConsoleIOService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepositoryJpa commentRepositoryJpa;
    private final ConsoleIOService consoleIOService;
    private final BookServiceImpl bookService;

    public CommentServiceImpl(CommentRepositoryJpa commentRepositoryJpa,
                              ConsoleIOService consoleIOService, BookServiceImpl bookService) {
        this.commentRepositoryJpa = commentRepositoryJpa;
        this.consoleIOService = consoleIOService;
        this.bookService = bookService;
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
        Book book = bookService.getById(bookId);
        if (book != null){
            commentRepositoryJpa.insert(new Comment(comment, book));
        }
        else {
            consoleIOService.outputString("Book with id - " + bookId + " not exist");
        }

    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepositoryJpa.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public void showAllByBookId(long bookId) {
        Book book = bookService.getById(bookId);
        consoleIOService.outputString("Book: " + book.getTitle() + ", comments: " + book.getComments());
    }
}
