package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;
import ru.otus.spring.domain.BookGenre;

import java.util.List;

@Service
public class BookServeceImpl implements BookService {

    private final BookDao bookDaoJdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookServeceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.authorDao = authorDao;
        this.bookDaoJdbc = bookDao;
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        return bookDaoJdbc.count();
    }

    @Override
    public int insert(String title, String author, String genre) {
        Book book = new Book(title, author, genre);
        authorDao.insert(new BookAuthor(author));
        genreDao.insert(new BookGenre(genre));
        return bookDaoJdbc.insert(book);
    }

    @Override
    public void updateById(String title, String author, String genre, int id) {
        Book book = new Book(title, author, genre);
        bookDaoJdbc.updateById(book, id);
    }

    @Override
    public void deleteById(int id) {
        bookDaoJdbc.deleteById(id);
    }

    @Override
    public List<Book> getByGenre(String genre) {
        return bookDaoJdbc.getByGenre(genre);
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return bookDaoJdbc.getByAuthor(author);
    }

    @Override
    public List<Book> getAll() {
        return bookDaoJdbc.getAll();
    }

    @Override
    public Book getById(int id) {
        return bookDaoJdbc.getById(id);
    }
}
