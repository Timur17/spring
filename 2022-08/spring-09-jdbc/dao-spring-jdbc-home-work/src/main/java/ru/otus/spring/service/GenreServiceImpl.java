package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.BookGenre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final BookDao bookDaoJdbc;

    public GenreServiceImpl(GenreDao genreDao, BookDao bookDaoJdbc) {
        this.bookDaoJdbc = bookDaoJdbc;
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        return genreDao.count();
    }

    @Override
    public int insert(String genre) {
        return genreDao.insert(new BookGenre(genre));
    }

    @Override
    public void updateById(String genre, int id) {
        genreDao.updateById(new BookGenre(genre), id);
    }

    @Override
    public void deleteById(int id) {
        genreDao.deleteById(id);
    }

    @Override
    public List<BookGenre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public BookGenre getById(int id) {
        return genreDao.getById(id);
    }

    @Override
    public BookGenre getByIdAllBooks(int id) {
        BookGenre bookGenre = genreDao.getById(id);
        bookGenre.setBooks(bookDaoJdbc.getByGenre(bookGenre.getGenre()));
        return bookGenre;
    }
}
