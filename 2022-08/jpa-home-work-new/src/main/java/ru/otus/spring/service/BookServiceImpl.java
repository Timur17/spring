package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public Book insert(String title, String author, String genre) {
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        if (optionalBook.isEmpty()) {
            return bookRepository.save(new Book(0, title, new Author(author), new Genre(genre)));
        } else {
            return null;
        }
    }

    @Override
    public Book updateById(String title, long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book book = optionalBook.orElse(null);
        if (book != null) {
            book.setTitle(title);
            return bookRepository.save(book);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> getById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        optionalBook.ifPresent(book -> {
            book.getComments().forEach(comment -> comment.getCommentBook());
        });
        return optionalBook;
    }
}
