package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с авторами ")
@SpringBootTest
@ContextConfiguration
class AuthorRepositoryTest {

    private static final long EXPECTED_AUTHORS_COUNT = 1;
    private static final String EXISTING_AUTHOR_ID = "1";
    private static final String EXISTING_BOOK_AUTHOR = "Tolstoy";

    @Autowired
    private AuthorRepository authorRepository;

    @DirtiesContext
    @DisplayName("Посчитать книги")
    @Test
    public void countTest() {
        long count = authorRepository.count();
        assertEquals(EXPECTED_AUTHORS_COUNT, count);
    }

    @DirtiesContext
    @DisplayName("Сохранить книгу в БД")
    @Test
    public void save() {
        Author expected = new Author("2", "newAuthor");
        authorRepository.save(expected);
        long count = authorRepository.count();
        assertEquals(EXPECTED_AUTHORS_COUNT + 1, count);

        Optional<Author> actualAuthor = authorRepository.findById("2");

        actualAuthor.ifPresent(author -> assertThat(author).usingRecursiveComparison().isEqualTo(expected));

    }

    @DirtiesContext
    @DisplayName("Обновить книгу в БД")
    @Test
    void updateTest() {
        String newAuthor = "newAuthor";
        Author expected = new Author(EXISTING_AUTHOR_ID, "newAuthor");
        authorRepository.save(expected);
        Optional<Author> actualAuthor = authorRepository.findById(EXISTING_AUTHOR_ID);
        assertFalse(actualAuthor.isEmpty());
        actualAuthor.ifPresent(author -> {
            assertEquals(EXISTING_AUTHOR_ID, author.getId());
            assertEquals(newAuthor, author.getAuthorBook());
        });
    }

    @DirtiesContext
    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void getByIdTest() {
        Optional<Author> actualAuthor = authorRepository.findById(EXISTING_AUTHOR_ID);
        assertFalse(actualAuthor.isEmpty());
        actualAuthor.ifPresent(author -> {
            assertEquals(EXISTING_AUTHOR_ID, author.getId());
            assertEquals(EXISTING_BOOK_AUTHOR, author.getAuthorBook());
        });
    }

    @DirtiesContext
    @DisplayName("возвращать ожидаемую книгу по title")
    @Test
    void getByAuthorTest() {
        Optional<Author> actualAuthor = authorRepository.findByAuthorBook(EXISTING_BOOK_AUTHOR);
        assertFalse(actualAuthor.isEmpty());
        actualAuthor.ifPresent(author -> {
            assertEquals(EXISTING_AUTHOR_ID, author.getId());
            assertEquals(EXISTING_BOOK_AUTHOR, author.getAuthorBook());
        });
    }

    @DirtiesContext
    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void getAllTest() {
        List<Author> authors = authorRepository.findAll();
        assertEquals(EXPECTED_AUTHORS_COUNT, authors.size());
    }

    @DirtiesContext
    @DisplayName("удалять заданного книгу по ее id")
    @Test
    void deleteById() {
        assertTrue(authorRepository.findById(EXISTING_AUTHOR_ID).isPresent());
        authorRepository.deleteById(EXISTING_AUTHOR_ID);
        assertFalse(authorRepository.findById(EXISTING_AUTHOR_ID).isPresent());
    }

}
