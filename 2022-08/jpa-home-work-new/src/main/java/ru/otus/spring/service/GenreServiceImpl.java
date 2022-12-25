//package ru.otus.spring.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.otus.spring.domain.Book;
//import ru.otus.spring.domain.Genre;
//import ru.otus.spring.repositories.GenreRepository;
//import ru.otus.spring.service.ioservice.ConsoleIOService;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class GenreServiceImpl implements GenreService {
//
//    private final GenreRepository genreRepository;
//    private final ConsoleIOService consoleIOService;
//
//    public GenreServiceImpl(GenreRepository genreRepository, ConsoleIOService consoleIOService) {
//        this.genreRepository = genreRepository;
//        this.consoleIOService = consoleIOService;
//    }
//
//    @Override
//    public long count() {
//        return genreRepository.count();
//    }
//
//    @Override
//    public Genre insert(String genre) {
//        Optional<Genre> optionalGenre = genreRepository.findByGenreBook(genre);
//        Genre entity = optionalGenre.orElse(null);
//        if (entity == null) {
//            return genreRepository.save(new Genre(genre));
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Genre updateById(String genre, long id) {
//        Optional<Genre> optionalGenre = genreRepository.findById(id);
//        Genre entity = optionalGenre.orElse(null);
//        if (entity != null) {
//            return genreRepository.save(new Genre(id, genre, entity.getBooks()));
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public void deleteById(long id) {
//        genreRepository.deleteById(id);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<Genre> getAll() {
//        List<Genre> genres = genreRepository.findAll();
//        genres.forEach(genre -> genre.getBooks().forEach(Book::getId));
//        return genres;
//
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public Optional<Genre> getById(long id) {
//        Optional<Genre> bookGenre = genreRepository.findById(id);
//        bookGenre.ifPresent(genre -> genre.getBooks().forEach(book -> {
//        }));
//        return bookGenre;
//    }
//
//}
