package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с жанрами ")
@SpringBootTest
class GenreRepositoryJpaTest {
    private static final long EXPECTED_GENRES_COUNT = 1;
    private static final String EXISTING_GENRE_ID = "1";
    private static final String EXISTING_BOOK_GENRE = "Historical novel";

    @Autowired
    private GenreRepository repository;

    @Test
    public void countTest() {
        long count = repository.count();
        assertEquals(EXPECTED_GENRES_COUNT, count);
    }

    @DisplayName("Сохранить жанр")
    @Test
    public void save() {
        Genre expected = new Genre("2", "newGenre",
                new ArrayList<>(List.of(new Book("newBook"))));
        repository.save(expected);
        long count = repository.count();
        assertEquals(EXPECTED_GENRES_COUNT + 1, count);

        Optional<Genre> actualGenre = repository.findById("2");
        actualGenre.ifPresent(genre -> assertThat(genre).usingRecursiveComparison().isEqualTo(expected));
    }

    @DisplayName("Обновить жанр")
    @Test
    void updateTest() {
        Genre expected = new Genre(EXISTING_GENRE_ID, "newGenre");
        repository.save(expected);
        Optional<Genre> actualGenre = repository.findById(EXISTING_GENRE_ID);
        assertFalse(actualGenre.isEmpty());
        actualGenre.ifPresent(genre -> {
            assertEquals(EXISTING_GENRE_ID, genre.getId());
            assertEquals(expected.getGenreBook(), genre.getGenreBook());
        });
    }


    @DisplayName("возвращать ожидаемый жанр по id")
    @Test
    void getByIdTest() {
        Genre expected = new Genre(EXISTING_GENRE_ID, EXISTING_BOOK_GENRE);
        Optional<Genre> actualGenre = repository.findById(EXISTING_GENRE_ID);
        assertFalse(actualGenre.isEmpty());
        actualGenre.ifPresent(genre -> {
            assertEquals(EXISTING_GENRE_ID, genre.getId());
            assertEquals(expected.getGenreBook(), genre.getGenreBook());
        });
    }


    @DisplayName("возвращать  ожидаемый жанр по title")
    @Test
    void getByGenreTest() {
        Genre expected = new Genre(EXISTING_GENRE_ID, EXISTING_BOOK_GENRE);
        Optional<Genre> actualGenre = repository.findByGenreBook(EXISTING_BOOK_GENRE);
        assertFalse(actualGenre.isEmpty());
        actualGenre.ifPresent(genre -> {
            assertEquals(genre.getId(), EXISTING_GENRE_ID);
            assertEquals(genre.getGenreBook(), expected.getGenreBook());
        });
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void getAllTest() {
        List<Genre> genres = repository.findAll();
        assertEquals(EXPECTED_GENRES_COUNT, genres.size());
    }


    @DisplayName("удалять заданный жанр по id")
    @Test
    void deleteById() {
        Optional<Genre> beforeGenre = repository.findById(EXISTING_GENRE_ID);
        assertTrue(beforeGenre.isPresent());

        repository.deleteById(EXISTING_GENRE_ID);
        Optional<Genre> afterGenre = repository.findById(EXISTING_GENRE_ID);
        assertFalse(afterGenre.isPresent());
    }

}