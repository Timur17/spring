package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.domain.Comment;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями ")
@SpringBootTest
class CommentRepositoryJpaTest {

    private static final long EXPECTED_COMMENT_COUNT = 1;
    private static final String EXISTING_COMMENT_ID = "1";
    private static final String EXISTING_BOOK_COMMENT = "The best book";

    @Autowired
    private CommentRepository repository;


    @Test
    public void countTest() {
        long count = repository.count();
        assertEquals(EXPECTED_COMMENT_COUNT, count);
    }

    @Test
    public void saveTest() {

        Comment expected = new Comment("2", "newComment");
        repository.save(expected);
        long count = repository.count();
        assertEquals(EXPECTED_COMMENT_COUNT + 1, count);

        Optional<Comment> actual = repository.findById("2");

        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("Обновить книгу в БД")
    @Test
    void updateTest() {
        Comment expected = new Comment(EXISTING_COMMENT_ID, "The best one");
        repository.save(expected);
        Optional<Comment> actual = repository.findById(EXISTING_COMMENT_ID);
        assertEquals(expected.getId(), actual.orElseThrow().getId());
        assertEquals(expected.getCommentBook(), actual.orElseThrow().getCommentBook());
    }

    @DisplayName("возвращать ожидаемую книгу по id")
    @Test
    void getByIdTest() {
        Optional<Comment> actual = repository.findById(EXISTING_COMMENT_ID);
        assertEquals(EXISTING_BOOK_COMMENT, actual.get().getCommentBook());
    }

    @DisplayName("удалять заданного книгу по ее id")
    @Test
    void deleteById() {
        assertTrue(repository.findById(EXISTING_COMMENT_ID).isPresent());
        repository.deleteById(EXISTING_COMMENT_ID);
        assertFalse(repository.findById(EXISTING_COMMENT_ID).isPresent());
    }

}
