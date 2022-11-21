package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {
    private static final int EXPECTED_BOOKS_COUNT = 1;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "war and peace";

    @Autowired
    private BookRepositoryJpa jpa;


    @Test
    public void countTest() {
        long count = jpa.count();
        assertEquals(EXPECTED_BOOKS_COUNT, count);
    }


    @Test
    public void save() {
        assertEquals(EXPECTED_BOOKS_COUNT, jpa.count());

        Book expectedBook = new Book(0, "title");
        jpa.insert(expectedBook);

        assertEquals(EXPECTED_BOOKS_COUNT + 1, jpa.count());

        Optional<Book> actualBook = jpa.getById(EXISTING_BOOK_ID + 1);

        assertEquals(expectedBook.getTitle(), actualBook.get().getTitle());
    }

    @DisplayName("Обновить книгу в БД")
    @Test
    void updateTest() {
        assertEquals(EXPECTED_BOOKS_COUNT, jpa.count());

        Book expectedBook = new Book(0, "title");

        jpa.updateById(expectedBook, EXISTING_BOOK_ID);
        Optional<Book> actualBook = jpa.getById(EXPECTED_BOOKS_COUNT);
        System.out.println(actualBook);
        assertEquals(EXPECTED_BOOKS_COUNT, jpa.count());
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void getByIdTest() {
        Optional<Book> actualBook = jpa.getById(EXPECTED_BOOKS_COUNT);
        assertEquals(EXISTING_BOOK_TITLE, actualBook.get().getTitle());
    }

    @DisplayName("возвращать ожидаемую книгу по title")
    @Test
    void getByTitleTest() {
        Book actualBook = jpa.getByTitle(EXISTING_BOOK_TITLE);
        assertEquals(EXISTING_BOOK_TITLE, actualBook.getTitle());
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void getAllTest() {
        List<Book> actualBookList = jpa.getAll();
        assertEquals(EXPECTED_BOOKS_COUNT, actualBookList.size());
    }


    @DisplayName("удалять заданного книгу по ее id")
    @Test
    void deleteById() {

        assertTrue(jpa.getById(EXISTING_BOOK_ID).isPresent());

        jpa.deleteById(EXPECTED_BOOKS_COUNT);

        assertTrue(jpa.getById(EXISTING_BOOK_ID).isEmpty());
    }

}