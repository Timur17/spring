package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Book;

import java.util.List;

@Service
public class BookServeceImpl implements BookService {

    private final BookDao bookDaoJdbc;

    public BookServeceImpl(BookDao bookDao) {
        this.bookDaoJdbc = bookDao;
    }

    @Override
    public int count() {
        int count = bookDaoJdbc.count();
        System.out.println("Amount books: " + count);
        return count;
    }

    @Override
    public int insert(String title, String author, String genre) {
        Book book = new Book(title, author, genre);
        Book bookInStore = bookDaoJdbc.getByTitle(title);
        if (bookInStore != null) {
            System.out.println("Store already has book with title: " + title + ", with id: " + bookInStore.getId());
            return bookInStore.getId();
        }
        int newId = bookDaoJdbc.insert(book);
        System.out.println("Book was added with id: " + newId);
        return newId;
    }

    @Override
    public void updateById(String title, String author, String genre, int id) {
        if (bookDaoJdbc.getById(id) == null) {
            System.out.println("Book with id: " + id + " not present in db so nothing was updated");
            return;
        }
        Book book = new Book(title, author, genre);
        bookDaoJdbc.updateById(book, id);
    }

    @Override
    public void deleteById(int id) {
        bookDaoJdbc.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = bookDaoJdbc.getAll();
        System.out.println("All books in library: ");
        books.forEach(System.out::println);
        return books;
    }

    @Override
    public Book getById(int id) {
        Book book = bookDaoJdbc.getById(id);
        System.out.println("books with id : " + id + " is " + book.getTitle());
        return book;
    }
}
