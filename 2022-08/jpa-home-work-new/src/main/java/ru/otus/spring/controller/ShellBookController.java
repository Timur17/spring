package ru.otus.spring.controller;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Controller;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ioservice.ConsoleIOService;

import java.util.List;
import java.util.Optional;

@ShellComponent
@Controller
public class ShellBookController {

    private final BookService bookService;
    private final ConsoleIOService consoleIOService;

    public ShellBookController(BookService bookService, ConsoleIOService consoleIOService) {
        this.bookService = bookService;
        this.consoleIOService = consoleIOService;
    }


    @ShellMethod(value = "Count books", key = {"count", "cb"})
    public void count() {
        consoleIOService.outputString("Amount books: " + bookService.count());
    }


    @ShellMethod(value = "Insert book", key = {"insertBook", "ib"})
    public void insert(String title, String author, String genre) {
        Book book = bookService.insert(title, author, genre);
        if (book == null) {
            consoleIOService.outputString("Store already has book - " + book);
        } else {
            consoleIOService.outputString("Book - " + book + " was added with id: " + book.getId());
        }
    }


    @ShellMethod(value = "Update book by id", key = {"updateBook", "ub"})
    public void update(String title, String id) {
        Book book = bookService.updateById(title, id);
        if (book == null) {
            consoleIOService.outputString("Book was not found with id: " + id);
            ;
        } else {
            consoleIOService.outputString("Book was updated, id:" + book.getId());
        }
    }

    @ShellMethod(value = "Delete book", key = {"deleteBook", "db"})
    public void deleteById(String id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Get all books", key = {"getAllBook", "gab"})
    public void getAll() {
        List<Book> books = bookService.getAll();
        consoleIOService.outputString("Amount books: " + books.size());
        books.forEach(book -> consoleIOService.outputString("id: " + book.getId() +
                ", title: " + book.getTitle() +
                ", author:  " + book.getAuthor().getAuthorBook() +
                ", genre: " + book.getGenre().getGenreBook()));
    }


    @ShellMethod(value = "Get book by id", key = {"getBook", "gb"})
    public void getById(String id) {
        Optional<Book> optionalBook = bookService.getById(id);
        Book book = optionalBook.orElse(null);
        if (book != null) {
            consoleIOService.outputString(book.toString() +
                    ", Author: " + book.getAuthor().getAuthorBook() +
                    ", Genre: " + book.getGenre().getGenreBook());
        } else {
            consoleIOService.outputString("Book not present with id: " + id);
        }
    }
}
