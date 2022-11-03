package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.AuthorServiceImpl;
import ru.otus.spring.service.GenreServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Dao для работы с книками должно")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class,
        GenreServiceImpl.class, AuthorServiceImpl.class})
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
    void countTest() {
        int actualBookCount = bookDaoJdbc.count();
        assertThat(actualBookCount).isEqualTo(EXPECTED_BOOKS_COUNT);
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void insertTest() {
        int countBeforeInsert = bookDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_BOOKS_COUNT);

        String title = "testTitle";
        Book expectedBook = new Book(title, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        int newId = bookDaoJdbc.insert(expectedBook);

        Book actualBook = bookDaoJdbc.getById(newId);

        assertThat(bookDaoJdbc.count()).isEqualTo(EXPECTED_BOOKS_COUNT + 1);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("Обновить книгу в БД")
    @Test
    void updateTest() {
        int countBeforeInsert = bookDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_BOOKS_COUNT);

        Book expectedBook = new Book("testTitle", EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        bookDaoJdbc.updateById(expectedBook, EXISTING_BOOK_ID);
        expectedBook.setId(EXISTING_BOOK_ID);

        int countAfterInsert = bookDaoJdbc.count();
        assertThat(countAfterInsert).isEqualTo(EXPECTED_BOOKS_COUNT);

        Book actualBook = bookDaoJdbc.getById(EXISTING_BOOK_ID);

        assertThat(bookDaoJdbc.count()).isEqualTo(EXPECTED_BOOKS_COUNT);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void getByIdTest() {
        Book book = bookDaoJdbc.getById(EXISTING_BOOK_ID);
        assertThat(book.getId()).usingRecursiveComparison().isEqualTo(EXISTING_BOOK_ID);
    }

    @DisplayName("возвращать ожидаемую книгу по title")
    @Test
    void getByTitleTest() {
        Book actualBook = bookDaoJdbc.getByTitle(EXISTING_BOOK_TITLE);
        assertThat(actualBook.getId()).usingRecursiveComparison().isEqualTo(EXISTING_BOOK_ID);
    }

    @DisplayName("удалять заданного книгу по ее id")
    @Test
    void deleteById() {
        int countBeforeDelete = bookDaoJdbc.count();
        assertThat(countBeforeDelete).isEqualTo(EXPECTED_BOOKS_COUNT);

        bookDaoJdbc.deleteById(EXISTING_BOOK_ID);

        int countAfterDelete = bookDaoJdbc.count();
        assertThat(countAfterDelete).isEqualTo(countBeforeDelete - 1);

        assertNull(bookDaoJdbc.getById(EXISTING_BOOK_ID));
    }

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void getAllTest() {
        Book expectedBook = new Book(EXISTING_BOOK_TITLE, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        expectedBook.setId(EXISTING_BOOK_ID);
        List<Book> actualBookList = bookDaoJdbc.getAll();
        assertThat(actualBookList)
                .containsExactlyInAnyOrder(expectedBook);
    }


}