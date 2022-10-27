package ru.otus.spring.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Controller;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.service.AuthorService;

@ShellComponent
@Controller
public class ShellAuthorController {

    private final AuthorService authorService;

    public ShellAuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @ShellMethod(value = "Count authors", key = {"countAuthors", "ca"})
    public int count() {
        int count = authorService.count();
        System.out.println("Amount authors: " + count);
        return count;
    }


    @ShellMethod(value = "Insert author", key = {"insertAuthors", "ia"})
    public void insert(String author) {
        int id = authorService.insert(author);
        System.out.println("Author was added with id: " + id);
    }


    @ShellMethod(value = "Update author by id", key = {"updateAuthor", "ua"})
    public void update(String author, int id) {
        authorService.updateById(author, id);
    }


    @ShellMethod(value = "Delete author", key = {"deleteAuthor", "da"})
    public void deleteById(int id) {
        authorService.deleteById(id);
    }

    @ShellMethod(value = "Get all authors", key = {"getAllAuthors", "gaa"})
    public void getAll() {
        System.out.println("All Authors in library:");
        authorService.getAll().forEach(author -> System.out.println(author.getAuthor()));
    }


    @ShellMethod(value = "Get author by id", key = {"getAuthor", "ga"})
    public void getById(int id) {
        System.out.println("author with id : " + id + " is " + authorService.getById(id));
    }

    @ShellMethod(value = "Get author by id and his books", key = {"getAuthorAndBooks", "gabb"})
    public void getByIdAuthorAndHisBooks(int id) {
        BookAuthor bookAuthor = authorService.getByIdAllHisBook(id);
        System.out.println("author with id : " + id + " is " + bookAuthor.getAuthor());
        bookAuthor.getBooks().forEach(System.out::println);
    }


}
