package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.BookGenre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        int count = genreDao.count();
        System.out.println("Amount genres: " + count);
        return count;
    }

    @Override
    public int insert(String genre) {
        BookGenre bookGenre = genreDao.getByGenre(genre);
        if (bookGenre == null) {
            bookGenre = new BookGenre(genre);
            int newId = genreDao.insert(bookGenre);
            System.out.println("Genre " + bookGenre.getGenre() + " was added with id: " + newId);
            bookGenre.setId(newId);
            return newId;
        } else {
            int id = bookGenre.getId();
            System.out.println("Store already has genre - " + genre + ", with id: " + id);
            return id;
        }
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
        System.out.println("All Genres in library:");
        List<BookGenre> genres = genreDao.getAll();
        genres.forEach(genre -> System.out.println("Genre: " + genre.getGenre() + ", books: " + genre.getBooks()));
        return genres;
    }

    @Override
    public BookGenre getById(int id) {
        BookGenre bookGenre = genreDao.getById(id);
        System.out.println("genre with id: " + id + " is " + bookGenre);
        return bookGenre;
    }

}
