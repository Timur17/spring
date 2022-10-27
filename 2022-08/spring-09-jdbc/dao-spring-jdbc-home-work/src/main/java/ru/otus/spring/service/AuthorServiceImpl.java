package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.BookAuthor;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final BookDao bookDaoJdbc;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public AuthorServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.authorDao = authorDao;
        this.bookDaoJdbc = bookDao;
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        return authorDao.count();
    }

    @Override
    public int insert(String author) {
        return authorDao.insert(new BookAuthor(author));
    }

    @Override
    public void updateById(String author, int id) {
        authorDao.updateById(new BookAuthor(author), id);
    }

    @Override
    public void deleteById(int id) {
        authorDao.deleteById(id);
    }

    @Override
    public List<BookAuthor> getAll() {
        return authorDao.getAll();
    }

    @Override
    public BookAuthor getById(int id) {
        return authorDao.getById(id);
    }

    @Override
    public BookAuthor getByIdAllHisBook(int id) {
        BookAuthor bookAuthor = authorDao.getById(id);
        bookAuthor.setBooks(bookDaoJdbc.getByAuthor(bookAuthor.getAuthor()));
        return bookAuthor;
    }
}
