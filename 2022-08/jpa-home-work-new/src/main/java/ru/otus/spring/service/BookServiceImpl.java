package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.service.ioservice.ConsoleIOService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final ConsoleIOService consoleIOService;

    public BookServiceImpl(BookRepositoryJpa bookRepositoryJpa, ConsoleIOService consoleIOService) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.consoleIOService = consoleIOService;
    }

    @Transactional(readOnly = true)
    @Override
    public void count() {
        long count = bookRepositoryJpa.count();
        consoleIOService.outputString("Amount books: " + count);
    }

    @Transactional
    @Override
    public void insert(String title, String author, String genre) {
        Book bookInStore = bookRepositoryJpa.getByTitle(title);
        if (bookInStore == null){
            Book book = bookRepositoryJpa.insert(new Book(0, title, new Author(author), new Genre(genre)));
            consoleIOService.outputString("Book was added with id: " + book.getId());
        }else {
            consoleIOService.outputString("Store already has book with title: " + title + ", with id: " + bookInStore.getId());
        }
    }

    @Transactional
    @Override
    public void updateById(String title, long id) {
        bookRepositoryJpa.updateById(new Book(title), id);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepositoryJpa.deleteById(id);
    }

    @Override
    public void showAll() {
        List<Book> books = bookRepositoryJpa.getAll();
        consoleIOService.outputString("Amount books: " + books.size());
        books.forEach(book -> consoleIOService.outputString(book.toString() +
                "id: " + book.getId() + " " + book.getAuthor().getAuthorBook() +
                ", " + book.getGenre().getGenreBook() + " " + book.getComments()));
    }

    @Override
    public Book getById(long id) {
        return bookRepositoryJpa.getById(id).get();
    }

    public void showById(long id) {
        Optional<Book> optional = bookRepositoryJpa.getById(id);
        optional.ifPresent(book -> consoleIOService.outputString("book id: " + id + ", title: " + book.getTitle()));
    }
}
