package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.BookAuthor;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public int count() {
        int count = authorDao.count();
        System.out.println("Amount authors: " + count);
        return count;
    }

    @Override
    public int insert(String author) {
        BookAuthor bookAuthor = authorDao.getByAuthor(author);
        if (bookAuthor == null) {
            bookAuthor = new BookAuthor(author);
            int id = authorDao.insert(bookAuthor);
            bookAuthor.setId(id);
            System.out.println("Author - " + author + " was added with id: " + id);
            return id;
        } else {
            int id = bookAuthor.getId();
            System.out.println("Store already has author - " + author + ", with id: " + id);
            return id;
        }

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
        System.out.println("All Authors in library:");
        List<BookAuthor> authors = authorDao.getAll();
        authors.forEach(author -> System.out.println("Author: " + author.getAuthor() + ", books: " + author.getBooks()));
        return authors;
    }

    @Override
    public BookAuthor getById(int id) {
        BookAuthor author = authorDao.getById(id);
        System.out.println("author with id : " + id + " is " + author);
        return author;
    }
}
