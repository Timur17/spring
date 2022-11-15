//package ru.otus.spring.repositories;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import ru.otus.spring.domain.Book;
//import ru.otus.spring.domain.Author;
//import ru.otus.spring.domain.Genre;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
//@DataJpaTest
//@Import(BookRepositoryJpa.class)
//class BookRepositoryJpaTest {
//
//    private static final int EXPECTED_BOOKS_COUNT = 1;
//    private static final int EXPECTED_AUTHORS_COUNT = 1;
//    private static final int EXPECTED_GENRES_COUNT = 1;
//    private static final int EXISTING_BOOK_ID = 1;
//    private static final String EXISTING_BOOK_TITLE = "war and peace";
//    private static final String EXISTING_BOOK_AUTHOR = "Tolstoy";
//    private static final String EXISTING_BOOK_GENRE = "Historical novel";
//
//    @Autowired
//    private BookRepositoryJpa jpa;
//
//
//    @Test
//    public void countTest(){
//        long count = jpa.count();
//        assertEquals(EXPECTED_BOOKS_COUNT, count);
//    }
//
//
//    @Test
//    public void save() {
//        long count = jpa.count();
//        assertEquals(EXPECTED_BOOKS_COUNT, count);
//
//        Author author = new Author(0, "newAuthor");
//        Genre genre = new Genre(0, "newGenre");
//        Book expectedBook = new Book(0, "title", author, genre);
//        jpa.insert(expectedBook);
//
//        long countAfter = jpa.count();
//        assertEquals(EXPECTED_BOOKS_COUNT + 1, countAfter);
//
//        Optional<Book> actualBook = jpa.getById(EXPECTED_BOOKS_COUNT + 1);
//
//        assertEquals(expectedBook.getTitle(), actualBook.get().getTitle());
//        assertEquals(expectedBook.getAuthor(), actualBook.get().getAuthor());
//        assertEquals(expectedBook.getGenre(), actualBook.get().getGenre());
//    }
//
//    @DisplayName("Обновить книгу в БД")
//    @Test
//    void updateTest() {
//        long count = jpa.count();
//        assertEquals(EXPECTED_BOOKS_COUNT, count);
//
//        Author author = new Author(0, "newAuthor");
//        Genre genre = new Genre(0, "newGenre");
//        Book expectedBook = new Book(0, "title", author, genre);
//
//        jpa.updateById(expectedBook, EXISTING_BOOK_ID);
//        Optional<Book> actualBook = jpa.getById(EXPECTED_BOOKS_COUNT);
//        System.out.println(actualBook);
//    }
//
//    @DisplayName("возвращать ожидаемую книгу по id")
//    @Test
//    void getByIdTest() {
//        Optional<Book> actualBook = jpa.getById(EXPECTED_BOOKS_COUNT);
//        assertEquals(EXISTING_BOOK_TITLE, actualBook.get().getTitle());
//        assertEquals(EXISTING_BOOK_AUTHOR, actualBook.get().getAuthor());
//        assertEquals(EXISTING_BOOK_GENRE, actualBook.get().getGenre());
//    }
//
//    @DisplayName("возвращать ожидаемую книгу по title")
//    @Test
//    void getByTitleTest() {
//        Book actualBook = jpa.getByTitle(EXISTING_BOOK_TITLE);
//        assertEquals(EXISTING_BOOK_TITLE, actualBook.getTitle());
//        assertEquals(EXISTING_BOOK_AUTHOR, actualBook.getAuthor());
//        assertEquals(EXISTING_BOOK_GENRE, actualBook.getGenre());
//    }
//
//    @DisplayName("возвращать ожидаемый список книг")
//    @Test
//    void getAllTest() {
//        List<Book> actualBookList = jpa.getAll();
//        assertEquals(EXPECTED_BOOKS_COUNT, actualBookList.size());
//    }
//
//
//    @DisplayName("удалять заданного книгу по ее id")
//    @Test
//    void deleteById() {
//        long countBeforeDelete = jpa.count();
//        assertEquals(EXPECTED_BOOKS_COUNT, countBeforeDelete);
//
//        jpa.deleteById(EXISTING_BOOK_ID);
//
//        long countAfterDelete = jpa.count();
//        assertThat(countAfterDelete).isEqualTo(countBeforeDelete - 1);
//
//        assertTrue(jpa.getById(EXISTING_BOOK_ID).isEmpty());
//    }
//
//}