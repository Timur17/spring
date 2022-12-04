package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.service.ioservice.ConsoleIOService;


import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final ConsoleIOService consoleIOService;

    public GenreServiceImpl(GenreRepository genreRepository, ConsoleIOService consoleIOService) {
        this.genreRepository = genreRepository;
        this.consoleIOService = consoleIOService;
    }

    @Transactional(readOnly = true)
    @Override
    public void count() {
        long count = genreRepository.count();
        consoleIOService.outputString("Amount genres: " + count);
    }

    @Transactional
    @Override
    public void insert(String genre) {
        Optional<Genre> optionalGenre = genreRepository.getByGenre(genre);
        Genre entity = optionalGenre.orElse(null);
        if (entity == null) {
            Genre insertedGenre = genreRepository.insert(new Genre(genre));
            consoleIOService.outputString("Genre " + insertedGenre.getGenreBook() + " was added with id: " + insertedGenre.getId());
        } else {
            consoleIOService.outputString("Store already has genre - " + genre + ", with id: " + entity.getId());
        }
    }

    @Transactional
    @Override
    public void updateById(String genre, int id) {
        Optional<Genre> optionalGenre = genreRepository.getById(id);
        Genre entity = optionalGenre.orElse(null);
        if (entity != null) {
            genreRepository.insert(new Genre(id, genre, entity.getBooks()));
        }
        else {
            consoleIOService.outputString("Genre was not found with id: " + id);
        }

    }

    @Transactional
    @Override
    public void deleteById(int id) {
        genreRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public void getAll() {
        System.out.println("All Genres in library:");
        List<Genre> genres = genreRepository.getAll();
        consoleIOService.outputString("Amount genres: " + genres.size());
        genres.forEach(genre -> consoleIOService.outputString("Genre: " + genre.getGenreBook() +
                ", genreId: " + genre.getId() +
                ", books: " + genre.getBooks()));
    }

    @Transactional(readOnly = true)
    @Override
    public void getById(int id) {
        Optional<Genre> bookGenre = genreRepository.getById(id);
        bookGenre.ifPresent(genre -> consoleIOService.outputString("genre with id : " + id + " is " + genre));

    }

}
