package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.CommentRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BookServiceImpl bookService;

    public CommentServiceImpl(CommentRepository commentRepository, BookServiceImpl bookService) {
        this.commentRepository = commentRepository;
        this.bookService = bookService;
    }


    @Override
    public long count() {
        return commentRepository.count();
    }

    @Transactional
    @Override
    public Comment insert(String comment, long bookId) {
        Optional<Book> optionalBook = bookService.getById(bookId);
        Book book = optionalBook.orElse(null);
        if (book != null) {
            return commentRepository.save(new Comment(comment, book));
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Set<Comment> getAllByBookId(long bookId) {
        Optional<Book> optionalBook = bookService.getById(bookId);
        Book book = optionalBook.orElse(null);
        if (book == null)
            return null;
        return book.getComments();
    }
}
