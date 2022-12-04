package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repositories.AuthorRepositoryJpa;
import ru.otus.spring.service.ioservice.ConsoleIOService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepositoryJpa repositoryJpa;
    private final ConsoleIOService consoleIOService;

    public AuthorServiceImpl(AuthorRepositoryJpa repositoryJpa, ConsoleIOService consoleIOService) {
        this.repositoryJpa = repositoryJpa;
        this.consoleIOService = consoleIOService;

    }

    @Transactional(readOnly = true)
    @Override
    public long count() {
        long count = repositoryJpa.count();
        consoleIOService.outputString("Amount authors: " + count);
        return count;
    }

    @Transactional
    @Override
    public void insert(String author) {
        Optional<Author> optionalAuthor = repositoryJpa.getByAuthor(author);
        Author entity = optionalAuthor.orElse(null);
        if (entity == null) {
            Author author1 = repositoryJpa.insert(new Author(author));
            consoleIOService.outputString("Author - " + author + " was added with id: " + author1.getId());
        } else {
            consoleIOService.outputString("Store already has author - " + author + " with id: " + entity.getId());
        }
    }

    @Transactional
    @Override
    public void updateById(String newAuthorName, int id) {
        Optional<Author> optionalAuthor = repositoryJpa.getById(id);
        Author entity = optionalAuthor.orElse(null);
        if (entity != null) {
            repositoryJpa.insert(new Author(id, newAuthorName, entity.getBooks()));
        } else {
            consoleIOService.outputString("Author was not found with id: " + id);
        }
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        repositoryJpa.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public  void showAll() {
        List<Author> authors = repositoryJpa.getAll();
        consoleIOService.outputString("Amount authors: " + authors.size());
        authors.forEach(author -> consoleIOService.outputString("Author: " + author.getAuthorBook()
                + ", authorId: " + author.getId()
                + ", books: " + author.getBooks()));
    }

    @Transactional(readOnly = true)
    @Override
    public void showById(int id) {
        Optional<Author> author = repositoryJpa.getById(id);
        author.ifPresent(author1 -> consoleIOService.outputString("author with id: " + id + " is " + author1));
    }
}
