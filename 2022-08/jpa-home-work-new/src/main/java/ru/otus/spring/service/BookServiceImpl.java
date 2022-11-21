package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.service.ioservice.ConsoleIOService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa bookRepositoryJpa;
    private final ConsoleIOService consoleIOService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookServiceImpl(BookRepositoryJpa bookRepositoryJpa, ConsoleIOService consoleIOService,
                           AuthorService authorService, GenreService genreService) {
        this.bookRepositoryJpa = bookRepositoryJpa;
        this.consoleIOService = consoleIOService;
        this.authorService = authorService;
        this.genreService = genreService;
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
            authorService.insert(author);
            genreService.insert(genre);
            Book book = bookRepositoryJpa.insert(new Book(title));
            consoleIOService.outputString("Book was added with id: " + book.getId());
        }else {
            consoleIOService.outputString("Store already has book with title: " + title + ", with id: " + bookInStore.getId());
        }
    }

    @Transactional
    @Override
    public void updateById(String title, int id) {
        bookRepositoryJpa.updateById(new Book(title), id);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        bookRepositoryJpa.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public void showAll() {
        List<Book> books = bookRepositoryJpa.getAll();
        consoleIOService.outputString("Amount books: " + books.size());
        books.forEach(book -> consoleIOService.outputString(book.toString()));
    }

    @Transactional(readOnly = true)
    @Override
    public void showById(int id) {
        Optional<Book> optional = bookRepositoryJpa.getById(id);
        optional.ifPresent(book -> consoleIOService.outputString("books with id : " + id + " is " + book.getTitle()));
    }
}
