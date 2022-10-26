package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.BookGenre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final int EXPECTED_GENRES_COUNT = 1;
    private static final int EXISTING_GENRES_ID = 1;
    private static final String EXISTING_GENRES_AUTHOR = "Historical novel";

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("возвращать ожидаемое количество жанров из БД")
    @Test
    void shouldReturnExpectedGenreCount() {
        int actualAuthors = genreDaoJdbc.count();
        assertThat(actualAuthors).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void shouldReturnExpectedGenreById() {
        BookGenre actualAuthor = genreDaoJdbc.getById(EXISTING_GENRES_ID);
        assertThat(actualAuthor.getId()).usingRecursiveComparison().isEqualTo(EXISTING_GENRES_ID);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenresList() {
        BookGenre expectedGenre = new BookGenre(EXISTING_GENRES_AUTHOR);
        expectedGenre.setId(EXISTING_GENRES_ID);
        List<BookGenre> actualGenreList = genreDaoJdbc.getAll();
        assertThat(actualGenreList)
                .containsExactlyInAnyOrder(expectedGenre);
    }

    @DisplayName("добавлять жанры в БД")
    @Test
    void shouldInsertGenre() {
        int countBeforeInsert = genreDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_GENRES_COUNT);

        BookGenre expectedGenre = new BookGenre("testGenre");
        int newId = genreDaoJdbc.insert(expectedGenre);

        BookGenre actualAuthor = genreDaoJdbc.getById(newId);

        assertThat(genreDaoJdbc.count()).isEqualTo(EXPECTED_GENRES_COUNT + 1);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("Обновить жанр в БД")
    @Test
    void shouldUpdateGenre() {
        int countBeforeInsert = genreDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_GENRES_COUNT);

        BookGenre expectedGenre = new BookGenre("testGenre");
        expectedGenre.setId(EXISTING_GENRES_ID);

        genreDaoJdbc.updateById(expectedGenre, EXISTING_GENRES_ID);


        BookGenre actualGenre = genreDaoJdbc.getById(EXISTING_GENRES_ID);

        assertThat(genreDaoJdbc.count()).isEqualTo(EXPECTED_GENRES_COUNT);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("удалять заданный жанр по ее id")
    @Test
    void shouldCorrectDeleteAuthorById() {
        int countBeforeDelete = genreDaoJdbc.count();
        assertThat(countBeforeDelete).isEqualTo(EXPECTED_GENRES_COUNT);

        genreDaoJdbc.deleteById(EXISTING_GENRES_ID);

        int countAfterDelete = genreDaoJdbc.count();
        assertThat(countAfterDelete).isEqualTo(countBeforeDelete - 1);

        assertThatThrownBy(() -> genreDaoJdbc.getById(EXISTING_GENRES_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }


}