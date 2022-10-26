package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.BookAuthor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import({AuthorDaoJdbc.class, BookDaoJdbc.class})
class AuthorDaoJdbcTest {

    private static final int EXPECTED_AUTHORS_COUNT = 1;
    private static final int EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_BOOK_AUTHOR = "Tolstoy";

    @Autowired
    AuthorDaoJdbc authorDaoJdbc;

    @DisplayName("возвращать ожидаемое количество авторов из БД")
    @Test
    void shouldReturnExpectedAuthorCount() {
        int actualAuthors = authorDaoJdbc.count();
        assertThat(actualAuthors).isEqualTo(EXPECTED_AUTHORS_COUNT);
    }

    @DisplayName("добавлять автора в БД")
    @Test
    void shouldInsertAuthor() {
        int countBeforeInsert = authorDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_AUTHORS_COUNT);

        BookAuthor expectedAuthor = new BookAuthor("Shakespeare");

        int newId = authorDaoJdbc.insert(expectedAuthor);
        int countAfterInsert = authorDaoJdbc.count();
        BookAuthor actualAuthor = authorDaoJdbc.getById(newId);

        assertThat(countAfterInsert).isEqualTo(EXPECTED_AUTHORS_COUNT + 1);
        assertThat(authorDaoJdbc.count()).isEqualTo(EXPECTED_AUTHORS_COUNT + 1);
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }

    @DisplayName("Обновить автора в БД")
    @Test
    void shouldUpdateAuthor() {
        int countBeforeInsert = authorDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_AUTHORS_COUNT);

        BookAuthor expectedAuthor = new BookAuthor("testAuthor");
        expectedAuthor.setId(EXISTING_AUTHOR_ID);

        authorDaoJdbc.updateById(expectedAuthor, EXISTING_AUTHOR_ID);

        BookAuthor actualAuthor = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);


        assertThat(authorDaoJdbc.count()).isEqualTo(EXPECTED_AUTHORS_COUNT);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемого автора по id")
    @Test
    void shouldReturnExpectedAuthorById() {
        BookAuthor actualAuthor = authorDaoJdbc.getById(EXISTING_AUTHOR_ID);

        assertThat(actualAuthor.getId()).isEqualTo(EXISTING_AUTHOR_ID);
        assertThat(actualAuthor.getAuthor()).isEqualTo(EXISTING_BOOK_AUTHOR);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        BookAuthor expectedBookAuthorBooksList = new BookAuthor(EXISTING_BOOK_AUTHOR);
        expectedBookAuthorBooksList.setId(EXISTING_AUTHOR_ID);
        List<BookAuthor> actualAuthorBooksList = authorDaoJdbc.getAll();
        assertThat(actualAuthorBooksList)
                .containsExactlyInAnyOrder(expectedBookAuthorBooksList);
    }

    @DisplayName("удалять заданного автора по по id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        int countBeforeDelete = authorDaoJdbc.count();
        assertThat(countBeforeDelete).isEqualTo(EXPECTED_AUTHORS_COUNT);

        authorDaoJdbc.deleteById(EXISTING_AUTHOR_ID);

        int countAfterDelete = authorDaoJdbc.count();
        assertThat(countAfterDelete).isEqualTo(countBeforeDelete - 1);

        assertThatThrownBy(() -> authorDaoJdbc.getById(EXISTING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

}