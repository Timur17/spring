package ru.otus.spring.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;

import java.util.List;

@Component
public class BookService {
    private final GenreDao genreDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public BookService(GenreDao genreDao, BookDao bookDao, AuthorDao authorDao) {
        this.genreDao = genreDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public List<Book> findBooksByGenre(String genre) {
        return bookDao.getByGenre(genre);
    }

    public List<Book> findBooksByAuthors(String author) {
        return bookDao.getByAuthor(author);
    }
}
