//package ru.otus.spring.repositories;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import ru.otus.spring.domain.Author;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
//@DataJpaTest
//@Import(AuthorRepositoryJpa.class)
//class AuthorRepositoryJpaTest {
//
//    private static final int EXPECTED_BOOKS_COUNT = 1;
//    private static final int EXPECTED_AUTHORS_COUNT = 1;
//    private static final int EXPECTED_GENRES_COUNT = 1;
//    private static final int EXISTING_BOOK_ID = 1;
//    private static final int EXISTING_AUTHOR_ID = 1;
//    private static final String EXISTING_BOOK_TITLE = "war and peace";
//    private static final String EXISTING_BOOK_AUTHOR = "Tolstoy";
//    private static final String EXISTING_BOOK_GENRE = "Historical novel";
//
//    @Autowired
//    private AuthorRepositoryJpa jpa;
//
//
//    @Test
//    public void countTest(){
//        long count = jpa.count();
//        assertEquals(EXPECTED_AUTHORS_COUNT, count);
//    }
//
//
//    @Test
//    public void save() {
//        Author author = new Author(0, "newAuthor");
//        jpa.insert(author);
//        long count = jpa.count();
//        assertEquals(EXPECTED_AUTHORS_COUNT + 1, count);
//    }
//
//    @DisplayName("Обновить книгу в БД")
//    @Test
//    void updateTest() {
//
//    }
//
//    @DisplayName("возвращать ожидаемую книгу по id")
//    @Test
//    void getByIdTest() {
//
//    }
//
//    @DisplayName("возвращать ожидаемую книгу по title")
//    @Test
//    void getByTitleTest() {
//        Optional<Author> author = jpa.getById(EXISTING_AUTHOR_ID);
//        System.out.println(author.get());
//
//    }
//
//    @DisplayName("возвращать ожидаемый список книг")
//    @Test
//    void getAllTest() {
//
//    }
//
//
//    @DisplayName("удалять заданного книгу по ее id")
//    @Test
//    void deleteById() {
//
//    }
//
//}