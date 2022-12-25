package ru.otus.spring.repositories;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;


//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookServiceImpl;
//
//import java.util.List;
//import java.util.Optional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@SpringBootTest
class BookRepositoryTest {
    private static final long EXPECTED_BOOKS_COUNT = 1;
    private static final String EXISTING_BOOK_ID = "1";
    private static final String EXISTING_BOOK_TITLE = "war and peace";


    @Autowired
    private BookRepository bookRepository;

    @Test
    public void countTest() {
        long count = bookRepository.count();
        assertEquals(EXPECTED_BOOKS_COUNT, count);
    }

    @Test
    public void save() {

        assertEquals(EXPECTED_BOOKS_COUNT, bookRepository.count());

        Book expectedBook = new Book( "title");
        bookRepository.save(expectedBook);

        assertEquals(EXPECTED_BOOKS_COUNT + 1, bookRepository.count());

        Optional<Book> actualBook = bookRepository.findByTitle("title");


        assertEquals(expectedBook.getTitle(), actualBook.orElseThrow().getTitle());
    }

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

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void getByIdTest() {
        Optional<Book> actualBook = bookRepository.findById(EXISTING_BOOK_ID);
        assertEquals(EXISTING_BOOK_TITLE, actualBook.orElseThrow().getTitle());
    }

    @DisplayName("возвращать ожидаемую книгу по title")
    @Test
    void getByTitleTest() {
        Optional<Book> actualBook = bookRepository.findByTitle(EXISTING_BOOK_TITLE);
        assertEquals(EXISTING_BOOK_TITLE, actualBook.orElseThrow().getTitle());
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void getAllTest() {
        List<Book> actualBookList = bookRepository.findAll();
        assertEquals(EXPECTED_BOOKS_COUNT, actualBookList.size());
    }


    @DisplayName("удалять заданного книгу по ее id")
    @Test
    void deleteById() {
        assertTrue(bookRepository.findById(EXISTING_BOOK_ID).isPresent());
        bookRepository.deleteById(EXPECTED_BOOKS_COUNT);
        assertTrue(bookRepository.findById(EXISTING_BOOK_ID).isEmpty());
    }

}