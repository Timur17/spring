package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import({AuthorDaoJdbc.class})
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 1;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR = "Tolstoy";
    private static final String EXISTING_BOOK_TITLE = "war and peace";
    private static final String EXISTING_BOOK_GENRE = "Historical novel";
    private static Book EXIST_BOOK;

    static {
        EXIST_BOOK = new Book(EXISTING_BOOK_TITLE, EXISTING_BOOK_AUTHOR, EXISTING_BOOK_GENRE);
        EXIST_BOOK.setId(EXISTING_BOOK_ID);
    }


    @Autowired
    AuthorDaoJdbc authorDaoJdbc;


    @DisplayName("возвращать ожидаемое количество авторов из БД")
    @Test
    void countTest() {
        int actualAuthors = authorDaoJdbc.count();
        assertThat(actualAuthors).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void insertTest() {
        int countBeforeInsert = authorDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_AUTHORS_COUNT);

        BookAuthor expectedAuthor = new BookAuthor("Shakespeare");

        int newId = authorDaoJdbc.insert(expectedAuthor);
        BookAuthor actualAuthor = authorDaoJdbc.getById(newId);


        assertThat(authorDaoJdbc.count()).isEqualTo(EXPECTED_AUTHORS_COUNT + 1);
        assertThat(actualAuthor.getAuthor()).isEqualTo(expectedAuthor.getAuthor());
    }

    @DisplayName("Обновить автора в БД")
    @Test
    void updateTest() {
        int countBeforeInsert = authorDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_AUTHORS_COUNT);

        BookAuthor expectedAuthor = new BookAuthor("testAuthor");
        expectedAuthor.setId(EXISTING_AUTHOR_ID);

        authorDaoJdbc.updateById(expectedAuthor, EXISTING_AUTHOR_ID);

        BookAuthor actualAuthor = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);


        assertThat(authorDaoJdbc.count()).isEqualTo(EXPECTED_AUTHORS_COUNT);
        assertThat(actualAuthor.getId()).isEqualTo(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor.getAuthor()).isEqualTo(expectedAuthor.getAuthor());
    }

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    void getByIdTest() {
        BookAuthor actualAuthor = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor.getId()).isEqualTo(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor.getAuthor()).isEqualTo(EXISTING_BOOK_AUTHOR);
        assertThat(actualAuthor.getBooks().get(0)).usingRecursiveComparison().isEqualTo(EXIST_BOOK);
    }

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    void getByAuthorTest() {
        BookAuthor actualAuthor = authorDaoJdbc.getByAuthor(EXISTING_BOOK_AUTHOR);
        assertThat(actualAuthor.getId()).isEqualTo(EXISTING_AUTHOR_ID);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void getAllTest() {
        BookAuthor expectedBookAuthorBooksList = new BookAuthor(EXISTING_BOOK_AUTHOR);
        expectedBookAuthorBooksList.setBooks(new ArrayList<>());
        expectedBookAuthorBooksList.getBooks().add(EXIST_BOOK);
        expectedBookAuthorBooksList.setId(EXISTING_AUTHOR_ID);

        List<BookAuthor> actualAuthorBooksList = authorDaoJdbc.getAll();
        assertThat(actualAuthorBooksList)
                .containsExactlyInAnyOrder(expectedBookAuthorBooksList);
    }

    @DisplayName("удалять заданного автора по по id")
    @Test
    void deleteById() {
        int countBeforeDelete = authorDaoJdbc.count();
        assertThat(countBeforeDelete).isEqualTo(EXPECTED_AUTHORS_COUNT);

        authorDaoJdbc.deleteById(EXISTING_AUTHOR_ID);

        int countAfterDelete = authorDaoJdbc.count();
        assertThat(countAfterDelete).isEqualTo(countBeforeDelete - 1);

        assertNull(authorDaoJdbc.getById(EXISTING_AUTHOR_ID));
    }
}