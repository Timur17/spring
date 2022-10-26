package ru.otus.spring.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Controller;
import ru.otus.spring.service.BookService;

@ShellComponent
@Controller
public class ShellBookService {

    private final BookService bookService;

    public ShellBookService(BookService bookService) {
        this.bookService = bookService;
    }


    @ShellMethod(value = "Count books", key = {"count", "c"})
    public int count() {
        int count = bookService.count();
        System.out.println("Amount books: " + count);
        return count;
    }


    @ShellMethod(value = "Insert book", key = {"insertBook", "ib"})
    public void insert(String title, String author, String genre) {
        bookService.insert(title, author, genre);
    }


    @ShellMethod(value = "Update book by id", key = {"updateBook", "ub"})
    public void update(String title, String author, String genre, int id) {
        bookService.updateById(title, author, genre, id);
    }


    @ShellMethod(value = "Delete book", key = {"deleteBook", "db"})
    public void deleteById(int id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Get all books", key = {"getAllBook", "gab"})
    public void getAll() {
        System.out.println("All books in library:");
        bookService.getAll().forEach(System.out::println);
    }


    @ShellMethod(value = "Get book by id", key = {"getBook", "gb"})
    public void getById(int id) {
        System.out.println("books with id : " + id + " is " + bookService.getById(id));
    }

    @ShellMethod(value = "Get books by author", key = {"getByAuthor", "gba"})
    public void getByAuthor(String author) {
        System.out.println("Books for author " + author);
        bookService.getByAuthor(author).forEach(System.out::println);
    }

    @ShellMethod(value = "Get books by genre", key = {"getByGenre", "gbg"})
    public void getByGenre(String genre) {
        System.out.println("Books for genre " + genre);
        bookService.getByGenre(genre).forEach(System.out::println);
    }

}
