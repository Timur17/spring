package ru.otus.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.dto.BookDto;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("/api/books")
    public List<BookDto> listPage() {
        List<BookDto> bookList = bookService.getAll().stream().map(BookDto::toDto).collect(Collectors.toList());
        return bookList;
    }

    @GetMapping("/books/{id}")
    public BookDto getBook(@PathVariable long id) {
        Optional<Book> book = bookService.getById(id);
        Book bookDb = book.orElseThrow(NotFoundException::new);
        return BookDto.toDto(bookDb);
    }
}
