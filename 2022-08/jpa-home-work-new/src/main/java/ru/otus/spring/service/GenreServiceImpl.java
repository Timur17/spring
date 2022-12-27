package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    public GenreServiceImpl(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public long count() {
        return genreRepository.count();
    }

    @Override
    public Genre insert(String genre) {
        Optional<Genre> optionalGenre = genreRepository.findByGenreBook(genre);
        Genre entity = optionalGenre.orElse(null);
        if (entity == null) {
            return genreRepository.save(new Genre(genre));
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public Genre updateById(String newGenre, String id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        Genre entity = optionalGenre.orElse(null);
        if (entity != null) {
            updateGenreBooks(entity.getGenreBook(), newGenre);
            return genreRepository.save(new Genre(id, newGenre, entity.getBooks()));
        } else {
            return null;
        }
    }

    public void updateGenreBooks(String genre, String newGenre) {
        List<Book> optionalBook = bookRepository.findAllByGenreGenreBook(genre);
        optionalBook.forEach(book -> {
            book.setGenre(new Genre(newGenre));
            bookRepository.save(book);
        });
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        Optional<Genre> genre = getById(id);
        genre.ifPresent(genre1 -> {
            bookRepository.deleteAllByGenreGenreBook(genre1.getGenreBook());
        });
        genreRepository.deleteById(id);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> getById(String id) {
        Optional<Genre> bookGenre = genreRepository.findById(id);
        bookGenre.ifPresent(genre -> {
            List<Book> books = bookRepository.findAllByGenreGenreBook(genre.getGenreBook());
            genre.setBooks(books);
        });
        return bookGenre;
    }

}
