package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataMongoTest
class BookRepositoryTest {
    private static final long EXPECTED_BOOKS_COUNT = 1;
    private static final String EXISTING_BOOK_ID = "1";
    private static final String EXISTING_BOOK_TITLE = "war and peace";
    private static final String EXISTING_BOOK_AUTHOR = "Tolstoy";
    private static final String EXISTING_BOOK_GENRE = "Historical novel";


    @Autowired
    private BookRepository bookRepository;

    @DirtiesContext
    @Test
    public void countTest() {
        long count = bookRepository.count();
        assertEquals(EXPECTED_BOOKS_COUNT, count);
    }

    @DirtiesContext
    @Test
    public void save() {

        assertEquals(EXPECTED_BOOKS_COUNT, bookRepository.count());

        Book expectedBook = new Book("title");
        bookRepository.save(expectedBook);

        assertEquals(EXPECTED_BOOKS_COUNT + 1, bookRepository.count());

        Optional<Book> actualBook = bookRepository.findByTitle("title");


        assertEquals(expectedBook.getTitle(), actualBook.orElseThrow().getTitle());
    }

    @DirtiesContext
    @DisplayName("Обновить книгу в БД")
    @Test
    void updateTest() {
        assertEquals(EXPECTED_BOOKS_COUNT, bookRepository.count());
        Optional<Book> optionalBook = bookRepository.findById(EXISTING_BOOK_ID);
        Book expectedBook = optionalBook.orElse(null);
        assertNotNull(expectedBook);
        expectedBook.setTitle("updated");

        bookRepository.save(expectedBook);
        Optional<Book> actualBook = bookRepository.findById(EXISTING_BOOK_ID);

        assertEquals(EXPECTED_BOOKS_COUNT, bookRepository.count());
        Book actual = actualBook.orElseThrow();
        assertEquals(EXISTING_BOOK_ID, actual.getId());
        assertEquals(expectedBook.getTitle(), actual.getTitle());
    }

    @DirtiesContext
    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void getByIdTest() {
        Optional<Book> actualBook = bookRepository.findById(EXISTING_BOOK_ID);
        assertEquals(EXISTING_BOOK_TITLE, actualBook.orElseThrow().getTitle());
    }

    @DirtiesContext
    @DisplayName("возвращать ожидаемую книгу по title")
    @Test
    void getByTitleTest() {
        Optional<Book> actualBook = bookRepository.findByTitle(EXISTING_BOOK_TITLE);
        assertEquals(EXISTING_BOOK_TITLE, actualBook.orElseThrow().getTitle());
    }

    @DirtiesContext
    @DisplayName("возвращать ожидаемые книги по автору")
    @Test
    void getAllByAuthorTest() {
        List<Book> bookList = bookRepository.findAllByAuthorAuthorBook(EXISTING_BOOK_AUTHOR);
        assertEquals(EXPECTED_BOOKS_COUNT, bookList.size());
        Book actualBook = bookList.get(0);
        assertEquals(EXISTING_BOOK_AUTHOR, actualBook.getAuthor().getAuthorBook());
        assertEquals(EXISTING_BOOK_GENRE, actualBook.getGenre().getGenreBook());
        assertEquals(EXISTING_BOOK_TITLE, actualBook.getTitle());
        assertEquals(EXISTING_BOOK_ID, actualBook.getId());
    }

    @DirtiesContext
    @DisplayName("возвращать ожидаемые книги по жанру")
    @Test
    void getAllByGenreTest() {
        List<Book> bookList = bookRepository.findAllByGenreGenreBook(EXISTING_BOOK_GENRE);
        assertEquals(EXPECTED_BOOKS_COUNT, bookList.size());
        Book actualBook = bookList.get(0);
        assertEquals(EXISTING_BOOK_AUTHOR, actualBook.getAuthor().getAuthorBook());
        assertEquals(EXISTING_BOOK_GENRE, actualBook.getGenre().getGenreBook());
        assertEquals(EXISTING_BOOK_TITLE, actualBook.getTitle());
        assertEquals(EXISTING_BOOK_ID, actualBook.getId());
    }

    @DirtiesContext
    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void getAllTest() {
        List<Book> actualBookList = bookRepository.findAll();
        assertEquals(EXPECTED_BOOKS_COUNT, actualBookList.size());
    }

    @DirtiesContext
    @DisplayName("удалять заданного книгу по ее id")
    @Test
    void deleteById() {
        assertTrue(bookRepository.findById(EXISTING_BOOK_ID).isPresent());
        bookRepository.deleteById(EXISTING_BOOK_ID);
        assertTrue(bookRepository.findById(EXISTING_BOOK_ID).isEmpty());
    }

    @DirtiesContext
    @DisplayName("удалять заданного книгу по ее id")
    @Test
    void deleteAllByAuthor() {
        assertEquals(bookRepository.findAllByAuthorAuthorBook(EXISTING_BOOK_AUTHOR).size(), 1);
        bookRepository.deleteAllByAuthorAuthorBook(EXISTING_BOOK_AUTHOR);
        assertEquals(bookRepository.findAllByAuthorAuthorBook(EXISTING_BOOK_AUTHOR).size(), 0);
    }

}