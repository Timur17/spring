package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;
    private final BookRepository bookRepository;


    public AuthorServiceImpl(AuthorRepository repository, BookRepository bookRepository) {
        this.repository = repository;
        this.bookRepository = bookRepository;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Transactional
    @Override
    public Author insert(String author) {
        Optional<Author> optionalAuthor = repository.findByAuthorBook(author);
        Author entity = optionalAuthor.orElse(null);
        if (entity == null) {
            return repository.save(new Author(author));
        }
        return null;
    }

    @Transactional
    @Override
    public Author updateById(String newAuthorName, String id) {
        Optional<Author> optionalAuthor = repository.findById(id);
        Author entity = optionalAuthor.orElse(null);
        if (entity != null) {
            updateAuthorBooks(entity.getAuthorBook(), newAuthorName);
            return repository.save(new Author(id, newAuthorName));
        } else {
            return null;
        }
    }

    public void updateAuthorBooks(String author, String newAuthor) {
        List<Book> optionalBook = bookRepository.findAllByAuthorAuthorBook(author);
        optionalBook.forEach(book -> {
            book.setAuthor(new Author(newAuthor));
            bookRepository.save(book);
        });
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        Optional<Author> optionalAuthor = getById(id);
        optionalAuthor.ifPresent(author -> {
            bookRepository.deleteAllByAuthorAuthorBook(author.getAuthorBook());
        });
        repository.deleteById(id);
    }

    @Override
    public List<Author> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Author> getById(String id) {
        Optional<Author> optionalAuthor = repository.findById(id);
        optionalAuthor.ifPresent(author -> {
            List<Book> books = bookRepository.findAllByAuthorAuthorBook(author.getAuthorBook());
            author.setBooks(books);
        });
        return optionalAuthor;
    }
}
