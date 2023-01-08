package ru.otus.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;
import ru.otus.spring.service.ioservice.ConsoleIOService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        var book = bookService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String editPerson(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        Book bookSaved = bookService.getById(book.getId()).orElseThrow(NotFoundException::new);
        bookService.updateById(book.getTitle(), bookSaved.getId());
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deletePage(@RequestParam("id") long id, Model model) {
        var book = bookService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "delete";
    }

    @PostMapping("/delete")
    public String deletePerson(Book book) {
        bookService.deleteById(book.getId());
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        List<Author> authors = authorService.getAll();
        List<Genre> genres = genreService.getAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("book", new Book());
        return "add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult,
                          Model model, Author author, Genre genre) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        Book bookSaved = bookService.insert(book.getTitle(), author.getAuthorBook(), genre.getGenreBook());
        if (bookSaved == null) {
            throw new AlreadyStoredException();
        }
        return "redirect:/";
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundException(NotFoundException e) {
        return ResponseEntity.badRequest().body("Book not present!");
    }

    @ExceptionHandler(AlreadyStoredException.class)
    public ResponseEntity<String> notFoundException(AlreadyStoredException e) {
        return ResponseEntity.badRequest().body("The book already stored!");
    }
}
