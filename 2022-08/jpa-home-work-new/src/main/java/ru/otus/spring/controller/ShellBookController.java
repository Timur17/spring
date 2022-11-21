package ru.otus.spring.controller;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Controller;
import ru.otus.spring.service.BookService;

@ShellComponent
@Controller
public class ShellBookController {

    private final BookService bookService;

    public ShellBookController(BookService bookService) {
        this.bookService = bookService;
    }


    @ShellMethod(value = "Count books", key = {"count", "cb"})
    public void count() {
        bookService.count();
    }


    @ShellMethod(value = "Insert book", key = {"insertBook", "ib"})
    public void insert(String title, String author, String genre) {
        bookService.insert(title, author, genre);
    }


    @ShellMethod(value = "Update book by id", key = {"updateBook", "ub"})
    public void update(String title, int id) {
        bookService.updateById(title, id);
    }


    @ShellMethod(value = "Delete book", key = {"deleteBook", "db"})
    public void deleteById(int id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Get all books", key = {"getAllBook", "gab"})
    public void getAll() {
        bookService.showAll();
    }


    @ShellMethod(value = "Get book by id", key = {"getBook", "gb"})
    public void getById(int id) {
        bookService.showById(id);
    }
}
