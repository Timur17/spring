package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final int EXPECTED_GENRES_COUNT = 1;
    private static final int EXISTING_GENRES_ID = 1;
    private static final String EXISTING_GENRES = "Historical novel";
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
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("возвращать ожидаемое количество жанров из БД")
    @Test
    void countTest() {
        int actualAuthors = genreDaoJdbc.count();
        assertThat(actualAuthors).isEqualTo(EXPECTED_GENRES_COUNT);
    }

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void getByIdTest() {
        BookGenre actualGenre = genreDaoJdbc.getById(EXISTING_GENRES_ID);
        assertThat(actualGenre.getId()).usingRecursiveComparison().isEqualTo(EXISTING_GENRES_ID);

    }

    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void getByGenreNameTest() {
        BookGenre genre = genreDaoJdbc.getByGenre(EXISTING_GENRES);
        assertThat(genre.getId()).isEqualTo(EXISTING_GENRES_ID);
        assertThat(genre.getGenre()).isEqualTo(EXISTING_GENRES);

    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void getAllTest() {
        BookGenre expectedGenre = new BookGenre(EXISTING_GENRES);
        expectedGenre.setBooks(new ArrayList<>());
        expectedGenre.getBooks().add(EXIST_BOOK);
        expectedGenre.setId(EXISTING_GENRES_ID);

        List<BookGenre> actualGenreList = genreDaoJdbc.getAll();
        assertThat(actualGenreList)
                .containsExactlyInAnyOrder(expectedGenre);
    }

    @DisplayName("добавлять жанры в БД")
    @Test
    void insertTest() {
        int countBeforeInsert = genreDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_GENRES_COUNT);

        BookGenre expectedGenre = new BookGenre("testGenre");
        int newId = genreDaoJdbc.insert(expectedGenre);

        BookGenre actualGenre = genreDaoJdbc.getById(newId);

        assertThat(genreDaoJdbc.count()).isEqualTo(EXPECTED_GENRES_COUNT + 1);
        assertThat(actualGenre.getGenre()).isEqualTo(expectedGenre.getGenre());
    }

    @DisplayName("Обновить жанр в БД")
    @Test
    void updateTest() {
        int countBeforeInsert = genreDaoJdbc.count();
        assertThat(countBeforeInsert).isEqualTo(EXPECTED_GENRES_COUNT);

        BookGenre expectedGenre = new BookGenre("testGenre");
        expectedGenre.setId(EXISTING_GENRES_ID);

        genreDaoJdbc.updateById(expectedGenre, EXISTING_GENRES_ID);


        BookGenre actualGenre = genreDaoJdbc.getById(EXISTING_GENRES_ID);

        assertThat(genreDaoJdbc.count()).isEqualTo(EXPECTED_GENRES_COUNT);
        assertThat(actualGenre.getGenre()).isEqualTo(expectedGenre.getGenre());
    }

    @DisplayName("удалять заданный жанр по ее id")
    @Test
    void deleteByIdTest() {
        int countBeforeDelete = genreDaoJdbc.count();
        assertThat(countBeforeDelete).isEqualTo(EXPECTED_GENRES_COUNT);

        genreDaoJdbc.deleteById(EXISTING_GENRES_ID);

        int countAfterDelete = genreDaoJdbc.count();
        assertThat(countAfterDelete).isEqualTo(countBeforeDelete - 1);

        assertNull(genreDaoJdbc.getById(EXISTING_GENRES_ID));
    }


}