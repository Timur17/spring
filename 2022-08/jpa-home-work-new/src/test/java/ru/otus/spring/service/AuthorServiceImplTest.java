package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.domain.Author;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Сервис для работы с авторами ")
@DataMongoTest
@Import({AuthorServiceImpl.class})
class AuthorServiceImplTest {

    private static final long EXPECTED_AUTHORS_COUNT = 1;
    private static final String EXISTING_AUTHOR_ID = "1";
    private static final String EXISTING_BOOK_AUTHOR = "Tolstoy";

    @Autowired
    private AuthorServiceImpl service;

    @DirtiesContext
    @Test
    void count() {
        assertEquals(EXPECTED_AUTHORS_COUNT, service.count());
    }

    @DirtiesContext
    @Test
    void insert() {
        String newAuthor = "newAuthor";
        Author author = service.insert(newAuthor);
        assertEquals(newAuthor, author.getAuthorBook());
        Author authorAddRepeat = service.insert(newAuthor);
        assertNull(authorAddRepeat);
    }

    @DirtiesContext
    @Test
    void updateById() {
        String newAuthor = "updAuthor";
        Author author = service.updateById(newAuthor, EXISTING_AUTHOR_ID);
        assertEquals(newAuthor, author.getAuthorBook());

        Author authorNotExist = service.updateById("authorNotExist", "NotExist");
        assertNull(authorNotExist);
    }

    @DirtiesContext
    @Test
    void deleteById() {
        Optional<Author> optionalAuthor = service.getById(EXISTING_AUTHOR_ID);
        Author author = optionalAuthor.orElse(null);
        assertNotNull(author);

        service.deleteById(EXISTING_AUTHOR_ID);

        Optional<Author> optionalAuthorAfterDelete = service.getById(EXISTING_AUTHOR_ID);
        Author authorAfterDelete = optionalAuthorAfterDelete.orElse(null);
        assertNull(authorAfterDelete);
    }

    @DirtiesContext
    @Test
    void getAll() {
        List<Author> authors = service.getAll();
        authors.forEach(author -> {
            assertNotNull(author);
            assertEquals(EXISTING_AUTHOR_ID, author.getId());
            assertEquals(EXISTING_BOOK_AUTHOR, author.getAuthorBook());
        });
    }

    @DirtiesContext
    @Test
    void getById() {
        Optional<Author> optionalAuthor = service.getById(EXISTING_AUTHOR_ID);
        Author author = optionalAuthor.orElse(null);
        assertNotNull(author);
        assertEquals(EXISTING_AUTHOR_ID, author.getId());
        assertEquals(EXISTING_BOOK_AUTHOR, author.getAuthorBook());

        Optional<Author> optionalAuthorWithUnknownId = service.getById("3");
        assertTrue(optionalAuthorWithUnknownId.isEmpty());
    }
}