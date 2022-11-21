package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с жанрами ")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {
    private static final int EXPECTED_GENRES_COUNT = 1;
    private static final int EXISTING_BOOK_ID = 1;
    private static final int EXISTING_GENRE_ID = 1;
    private static final long EXISTING_COMMENT_ID = 1;
    private static final String EXISTING_BOOK_TITLE = "war and peace";
    private static final String EXISTING_BOOK_GENRE = "Historical novel";
    private static final String EXISTING_BOOK_COMMENT = "The best book";

    @Autowired
    private GenreRepositoryJpa jpa;


    @Test
    public void countTest() {
        long count = jpa.count();
        assertEquals(EXPECTED_GENRES_COUNT, count);
    }

    @DisplayName("Сохранить жанр")
    @Test
    public void save() {
        Genre expected = new Genre(0, "newGenre",
                new ArrayList<>(List.of(new Book(0, "newBook"))));
        jpa.insert(expected);
        long count = jpa.count();
        assertEquals(EXPECTED_GENRES_COUNT + 1, count);

        expected.setId(EXPECTED_GENRES_COUNT + 1);

        Optional<Genre> actualGenre = jpa.getById(EXISTING_GENRE_ID + 1);

        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("Обновить жанр")
    @Test
    void updateTest() {
        Genre expected = new Genre(EXISTING_GENRE_ID, "newGenre",
                new ArrayList<>(List.of(new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE,
                        Set.of(new Comment(EXISTING_COMMENT_ID, EXISTING_BOOK_COMMENT, EXISTING_BOOK_ID))))));

        jpa.updateById(expected, EXISTING_BOOK_ID);

        Optional<Genre> actualGenre = jpa.getById(EXISTING_GENRE_ID);
        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expected);
    }


    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void getByIdTest() {
        Genre expected = new Genre(EXISTING_GENRE_ID, EXISTING_BOOK_GENRE,
                new ArrayList<>(List.of(new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE,
                        Set.of(new Comment(EXISTING_COMMENT_ID, EXISTING_BOOK_COMMENT, EXISTING_BOOK_ID))))));
        Optional<Genre> actualGenre = jpa.getById(EXISTING_GENRE_ID);
        assertEquals(EXISTING_BOOK_GENRE, actualGenre.get().getGenre());
        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expected);
    }


    @DisplayName("возвращать  ожидаемый жанр по title")
    @Test
    void getByAuthorTest() {
        Genre expected = new Genre(EXISTING_GENRE_ID, EXISTING_BOOK_GENRE,
                new ArrayList<>(List.of(new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE,
                        Set.of(new Comment(EXISTING_COMMENT_ID, EXISTING_BOOK_COMMENT, EXISTING_BOOK_ID))))));
        Optional<Genre> actualGenre = jpa.getByGenre(EXISTING_BOOK_GENRE);
        assertEquals(EXISTING_BOOK_GENRE, actualGenre.get().getGenre());
        assertThat(actualGenre.get()).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void getAllTest() {
        List<Genre> genres = jpa.getAll();
        assertEquals(EXPECTED_GENRES_COUNT, genres.size());
    }


    @DisplayName("удалять заданный жанр по id")
    @Test
    void deleteById() {
        Optional<Genre> beforeGenre = jpa.getById(EXISTING_GENRE_ID);
        assertTrue(beforeGenre.isPresent());

        jpa.deleteById(EXISTING_GENRE_ID);

        Optional<Genre> afterGenre = jpa.getById(EXISTING_GENRE_ID);
        assertFalse(afterGenre.isPresent());
    }

}