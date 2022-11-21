package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями ")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
class CommentRepositoryJpaTest {

    private static final int EXPECTED_COMMENT_COUNT = 1;
    private static final int EXISTING_COMMENT_ID = 1;
    private static final int EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_COMMENT = "The best book";

    @Autowired
    private CommentRepositoryJpa jpa;


    @Test
    public void countTest() {
        long count = jpa.count();
        assertEquals(EXPECTED_COMMENT_COUNT, count);
    }


    @Test
    public void save() {

        Comment expected = new Comment("newComment");
        jpa.insert(expected);
        long count = jpa.count();
        assertEquals(EXPECTED_COMMENT_COUNT + 1, count);

        expected.setId(EXISTING_COMMENT_ID + 1);

        Optional<Comment> actual = jpa.getById(EXISTING_COMMENT_ID + 1);

        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void saveWithBookId() {

        Comment expected = new Comment("newComment", EXISTING_BOOK_ID);
        jpa.insert(expected);
        long count = jpa.count();
        assertEquals(EXPECTED_COMMENT_COUNT + 1, count);

        expected.setId(EXISTING_COMMENT_ID + 1);

        Optional<Comment> actual = jpa.getById(EXISTING_COMMENT_ID + 1);

        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("Обновить книгу в БД")
    @Test
    void updateTest() {

        Comment expected = new Comment(EXISTING_COMMENT_ID, "The best one", EXISTING_BOOK_ID);
        jpa.updateById(expected, EXISTING_COMMENT_ID);

        Optional<Comment> actual = jpa.getById(EXISTING_COMMENT_ID);
        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void getByIdTest() {
        Optional<Comment> actual = jpa.getById(EXISTING_COMMENT_ID);
        assertEquals(EXISTING_BOOK_COMMENT, actual.get().getComment());
    }

    @DisplayName("возвращать комментарии по id книги")
    @Test
    void getAllByBookIdTest() {
        List<Comment> actual = jpa.getAllByBookId(EXISTING_BOOK_ID);
        assertEquals(EXPECTED_COMMENT_COUNT, actual.size());
    }


    @DisplayName("удалять заданного книгу по ее id")
    @Test
    void deleteById() {
        assertTrue(jpa.getById(EXISTING_COMMENT_ID).isPresent());
        jpa.deleteById(EXISTING_COMMENT_ID);
        assertFalse(jpa.getById(EXISTING_COMMENT_ID).isPresent());
    }

}
