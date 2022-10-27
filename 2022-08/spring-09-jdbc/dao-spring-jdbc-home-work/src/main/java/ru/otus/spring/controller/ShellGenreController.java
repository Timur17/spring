package ru.otus.spring.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Controller;
import ru.otus.spring.domain.BookGenre;
import ru.otus.spring.service.GenreService;

@ShellComponent
@Controller
public class ShellGenreController {

    private final GenreService genreService;

    public ShellGenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @ShellMethod(value = "Count genres", key = {"countGenres", "cg"})
    public int count() {
        int count = genreService.count();
        System.out.println("Amount genres: " + count);
        return count;
    }


    @ShellMethod(value = "Insert genre", key = {"insertGenres", "ig"})
    public void insert(String genre) {
        int id = genreService.insert(genre);
        System.out.println("Genre was added with id: " + id);
    }


    @ShellMethod(value = "Update genre by id", key = {"updateGenre", "ug"})
    public void update(String genre, int id) {
        genreService.updateById(genre, id);
    }


    @ShellMethod(value = "Delete genre", key = {"deleteGenre", "dg"})
    public void deleteById(int id) {
        genreService.deleteById(id);
    }

    @ShellMethod(value = "Get all genres", key = {"getAllGenres", "gag"})
    public void getAll() {
        System.out.println("All Genres in library:");
        genreService.getAll().forEach(System.out::println);
    }


    @ShellMethod(value = "Get genre by id", key = {"getGenre", "gg"})
    public void getById(int id) {
        System.out.println("genre with id : " + id + " is " + genreService.getById(id));
    }


    @ShellMethod(value = "Get books by id genre ", key = {"getAuthorAndBooks", "gbbg"})
    public void getBooksByIdGenre(int id) {
        BookGenre bookGenre = genreService.getByIdAllBooks(id);
        System.out.println("genre with id : " + id + " is " + bookGenre.getGenre());
        bookGenre.getBooks().forEach(System.out::println);
    }
}
