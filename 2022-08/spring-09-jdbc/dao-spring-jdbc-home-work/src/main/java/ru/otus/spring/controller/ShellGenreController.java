package ru.otus.spring.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Controller;
import ru.otus.spring.service.GenreService;

@ShellComponent
@Controller
public class ShellGenreController {

    private final GenreService genreService;

    public ShellGenreController(GenreService genreService) {
        this.genreService = genreService;
    }


    @ShellMethod(value = "Count genres", key = {"countGenres", "cg"})
    public void count() {
        genreService.count();
    }


    @ShellMethod(value = "Insert genre", key = {"insertGenres", "ig"})
    public void insert(String genre) {
        genreService.insert(genre);
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
        genreService.getAll();
    }


    @ShellMethod(value = "Get genre by id", key = {"getGenre", "gg"})
    public void getById(int id) {
        genreService.getById(id);
    }
}
