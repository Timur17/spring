package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Сервис для работы с книгами")
@DataMongoTest
@Import({BookServiceImpl.class, AuthorServiceImpl.class, GenreServiceImpl.class, CommentServiceImpl.class})
class BookServiceImplTest {
    private static final int EXPECTED_BOOK_COUNT = 1;
    private static final String EXPECTED_BOOK_ID = "1";

    @Autowired
    BookServiceImpl service;

    @DirtiesContext
    @Test
    void count() {
        assertEquals(EXPECTED_BOOK_COUNT, service.count());
    }

    @DirtiesContext
    @Test
    void insert() {
        String newBook = "newBook";
        String newAuthor = "newAuthor";
        String newGenre = "newGenre";

        Book book = service.insert(newBook, newAuthor, newGenre);
        assertEquals(newBook, book.getTitle());
        assertEquals(newAuthor, book.getAuthor().getAuthorBook());
        assertEquals(newGenre, book.getGenre().getGenreBook());

        Book bookAddRepeat = service.insert(newBook, newAuthor, newGenre);
        assertNull(bookAddRepeat);
    }

    @DirtiesContext
    @Test
    void updateById() {
        String newBook = "updBook";
        Book book = service.updateById(newBook, EXPECTED_BOOK_ID);
        assertEquals(newBook, book.getTitle());

        Book bookNotExistId = service.updateById(newBook, "NotExistId");
        assertNull(bookNotExistId);

    }

    @DirtiesContext
    @Test
    void deleteById() {
        Optional<Book> optionalBook = service.getById(EXPECTED_BOOK_ID);
        Book book = optionalBook.orElse(null);
        assertNotNull(book);

        service.deleteById(EXPECTED_BOOK_ID);

        Optional<Book> optionalBookAfterDelete = service.getById(EXPECTED_BOOK_ID);
        Book bookAfterDelete = optionalBookAfterDelete.orElse(null);
        assertNull(bookAfterDelete);
    }

    @DirtiesContext
    @Test
    void getAll() {
        List<Book> books = service.getAll();
        books.forEach(book -> {
            assertNotNull(book);
            assertNotNull(book.getTitle());
            assertNotNull(book.getAuthor());
            assertNotNull(book.getGenre());
        });
    }

    @DirtiesContext
    @Test
    void getById() {
        Optional<Book> optionalBook = service.getById(EXPECTED_BOOK_ID);
        Book book = optionalBook.orElse(null);
        assertNotNull(book);
        assertNotNull(book.getTitle());
        assertNotNull(book.getAuthor().getAuthorBook());
        assertNotNull(book.getGenre().getGenreBook());
        Optional<Book> optionalBookNotExistId = service.getById("NotExistId");
        assertFalse(optionalBookNotExistId.isPresent());

    }
}