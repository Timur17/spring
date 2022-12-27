package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentServiceImpl commentService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService,
                           GenreService genreService, CommentServiceImpl commentService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Transactional
    @Override
    public Book insert(String title, String author, String genre) {
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        if (optionalBook.isEmpty()) {
            authorService.insert(author);
            genreService.insert(genre);
            return bookRepository.save(new Book(title, new Author(author), new Genre(genre)));
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public Book updateById(String title, String id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book book = optionalBook.orElse(null);
        if (book != null) {
            book.setTitle(title);
            return bookRepository.save(book);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        deleteAllCommentsByBookId(id);
        bookRepository.deleteById(id);
    }

    public void deleteAllCommentsByBookId(String id) {
        List<Comment> comments = commentService.getAllByBookId(id);
        comments.stream().forEach(comment -> commentService.deleteById(comment.getId()));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getById(String id) {
        return bookRepository.findById(id);
    }
}
