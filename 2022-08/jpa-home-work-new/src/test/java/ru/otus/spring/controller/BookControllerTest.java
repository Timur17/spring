package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.GenreService;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void listTest() throws Exception {
        List<Book> books = List.of(new Book(1, "war and peace", new Author("Tolstoy"), new Genre("Historical novel")),
                new Book(2, "Gamlet", new Author("Shakespeare"), new Genre("tragedy")));
        given(bookService.getAll()).willReturn(books);

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"));
    }

    @Test
    void editGetTest() throws Exception {
        long id = 1;
        Book book = new Book(1, "war and peace", new Author("Tolstoy"),
                new Genre("Historical novel"));
        given(bookService.getById(id)).willReturn(Optional.of(book));

        mvc.perform(get("/edit?id={id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void editPostTest() throws Exception {
        long id = 1;
        Book book = new Book(1, "war and peace", new Author("Tolstoy"),
                new Genre("Historical novel"));
        given(bookService.getById(id)).willReturn(Optional.of(book));

        mvc.perform(post("/edit")
                .param("id", "1")
                .param("title", "new"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteGetTest() throws Exception {
        long id = 1;
        Book book = new Book(1, "war and peace", new Author("Tolstoy"),
                new Genre("Historical novel"));
        given(bookService.getById(id)).willReturn(Optional.of(book));

        mvc.perform(get("/delete?id={id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("delete"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void deletePostTest() throws Exception {
        mvc.perform(post("/delete")
                .param("id", "1")
                .param("title", "new"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void addGetTest() throws Exception {
        mvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    void addPostTest() throws Exception {
        Book book = new Book(1, "war and peace", new Author("Tolstoy"),
                new Genre("Historical novel"));
        given(bookService.insert("war and peace", "Tolstoy", "Historical novel")).willReturn(book);

        mvc.perform(post("/add")
                .param("title", "war and peace")
                .param("authorBook", "Tolstoy")
                .param("genreBook", "Historical novel"))
                .andExpect(status().is3xxRedirection());
    }

}