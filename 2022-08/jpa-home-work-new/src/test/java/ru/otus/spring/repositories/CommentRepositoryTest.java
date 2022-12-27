package ru.otus.spring.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.domain.Comment;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с комментариями ")
@DataMongoTest
class CommentRepositoryTest {

    private static final long EXPECTED_COMMENT_COUNT = 1;
    private static final String EXISTING_COMMENT_ID = "1";
    private static final String EXISTING_BOOK_COMMENT = "The best book";
    private static final String EXISTING_BOOK_ID = "1";
    private static final String EXISTING_BOOK_Title = "war and peace";

    @Autowired
    private CommentRepository repository;


    @Test
    public void countTest() {
        long count = repository.count();
        assertEquals(EXPECTED_COMMENT_COUNT, count);
    }

    @Test
    public void saveTest() {

        Comment expected = new Comment("2", "newComment", EXISTING_BOOK_ID);
        repository.save(expected);
        long count = repository.count();
        assertEquals(EXPECTED_COMMENT_COUNT + 1, count);

        Optional<Comment> actual = repository.findById("2");

        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("Обновить комментарий в БД")
    @Test
    void updateTest() {
        Comment expected = new Comment(EXISTING_COMMENT_ID, "The best one", EXISTING_BOOK_ID);
        repository.save(expected);
        Optional<Comment> actual = repository.findById(EXISTING_COMMENT_ID);
        assertEquals(expected.getId(), actual.orElseThrow().getId());
        assertEquals(expected.getCommentBook(), actual.orElseThrow().getCommentBook());
        assertEquals(expected.getBookId(), actual.orElseThrow().getBookId());
    }

    @DisplayName("возвращать ожидаемый коммент по id")
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

    @DisplayName("найти все комменты по книге")
    @Test
    void findAllByBookTitle() {
        assertEquals(repository.findAllByBookId("1").size(), EXPECTED_COMMENT_COUNT);
    }

}
