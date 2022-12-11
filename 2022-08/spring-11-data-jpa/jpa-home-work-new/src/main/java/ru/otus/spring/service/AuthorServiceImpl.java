package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Transactional
    @Override
    public Author insert(String author) {
        Optional<Author> optionalAuthor = repository.findByAuthor(author);
        Author entity = optionalAuthor.orElse(null);
        if (entity == null) {
            return repository.save(new Author(author));
        }
        return null;
    }

    @Transactional
    @Override
    public Author updateById(String newAuthorName, long id) {
        Optional<Author> optionalAuthor = repository.findById(id);
        Author entity = optionalAuthor.orElse(null);
        if (entity != null) {
            return repository.save(new Author(id, newAuthorName, entity.getBooks()));
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }


    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        List<Author> authors = repository.findAll();
        authors.forEach(author -> author.getBooks().forEach(book -> {}));
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> getById(long id) {
        Optional<Author> optionalAuthor = repository.findById(id);
        optionalAuthor.ifPresent(author -> author.getBooks().forEach(book -> {
        }));
        return repository.findById(id);
    }
}
