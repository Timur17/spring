package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с книками должно")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 1;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "war and peace";
    private static final String EXISTING_BOOK_AUTHOR = "Tolstoy";
    private static final String EXISTING_BOOK_GENRE = "Historical novel";

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @DisplayName("возвращать ожидаемое количество книг из БД")
    @Test
    void shouldReturnExpectedBookCount() {
        int actualBookCount = bookDaoJdbc.count();
        assertThat(actualBookCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        int countBeforeInsert = bookDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_BOOKS_COUNT);

        String title = "testTitle";
        Book expectedBook = new Book(title, "testAuthor", "testGenre");
        int newId = bookDaoJdbc.insert(expectedBook);

        Book actualBook = bookDaoJdbc.getById(newId);

        assertThat(bookDaoJdbc.count()).isEqualTo(EXPECTED_BOOKS_COUNT + 1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Обновить книгу в БД")
    @Test
    void shouldUpdateBook() {
        int countBeforeInsert = bookDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_BOOKS_COUNT);

        String title = "testTitle";
        String author = "testAuthor";
        String genre = "testGenre";
        Book  expectedBook = new Book(title, author, genre);

        bookDaoJdbc.updateById(expectedBook, EXISTING_BOOK_ID);
        expectedBook.setId(EXISTING_BOOK_ID);

        Book actualBook = bookDaoJdbc.getById(EXISTING_BOOK_ID);

        System.out.println("Test::: " + expectedBook);

        assertThat(bookDaoJdbc.count()).isEqualTo(EXPECTED_BOOKS_COUNT);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        Book actualBook = bookDaoJdbc.getById(EXISTING_BOOK_ID);
        assertThat(actualBook.getId()).usingRecursiveComparison().isEqualTo(EXISTING_BOOK_ID);
    }

    @DisplayName("возвращать ожидаемую книгу по Атору")
    @Test
    void shouldReturnExpectedBookByAuthor() {
        List<Book> actualBook = bookDaoJdbc.getByAuthor(EXISTING_BOOK_AUTHOR);
        assertThat(actualBook.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("возвращать ожидаемую книгу по Жанру")
    @Test
    void shouldReturnExpectedBookByGenre() {
        List<Book> actualBook = bookDaoJdbc.getByGenre(EXISTING_BOOK_GENRE);
        assertThat(actualBook.size()).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("удалять заданного книгу по ее id")
    @Test
    void shouldCorrectDeleteBookById() {
        int countBeforeDelete = bookDaoJdbc.count();
        assertThat(countBeforeDelete).isEqualTo(EXPECTED_BOOKS_COUNT);

        bookDaoJdbc.deleteById(EXISTING_BOOK_ID);

        int countAfterDelete = bookDaoJdbc.count();
        assertThat(countAfterDelete).isEqualTo(countBeforeDelete - 1);

        assertThatThrownBy(() -> bookDaoJdbc.getById(EXISTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        Book expectedBook = new Book(EXISTING_BOOK_TITLE, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        expectedBook.setId(EXISTING_BOOK_ID);
        List<Book> actualBookList = bookDaoJdbc.getAll();
        assertThat(actualBookList)
                .containsExactlyInAnyOrder(expectedBook);
    }


}