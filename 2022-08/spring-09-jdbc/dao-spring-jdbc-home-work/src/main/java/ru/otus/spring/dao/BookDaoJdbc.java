package ru.otus.spring.dao;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.AuthorServiceImpl;
import ru.otus.spring.service.GenreService;
import ru.otus.spring.service.GenreServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final GenreService genreService;
    private final AuthorService authorService;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations,
                       GenreServiceImpl genreService, AuthorServiceImpl authorService) {
        this.jdbc = jdbcOperations;
        this.genreService = genreService;
        this.authorService = authorService;
    }


    @Override
    public int count() {
        Integer amount = jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
        return isNull(amount) ? 0 : amount;
    }

    @Override
    public int insert(Book book) {
        int authorId = authorService.insert(book.getAuthor());
        int genreId = genreService.insert(book.getGenre());
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author_id", authorId);
        params.addValue("genre_id", genreId);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("INSERT INTO books (title, author_id, genre_id) values (:title, :author_id, :genre_id)",
                params, keyHolder);
        int id = isNull(keyHolder.getKey()) ? 0 : keyHolder.getKey().intValue();
        book.setId(id);
        return id;
    }

    @Override
    public void updateById(Book book, int id) {
        int authorId = authorService.insert(book.getAuthor());
        int genreId = genreService.insert(book.getGenre());

        jdbc.update("update books set title = :title, author_id = :author_id, genre_id = :genre_id where id = :id",
                Map.of("id", id, "title", book.getTitle(),
                        "author_id", authorId, "genre_id", genreId));

    }

    @Override
    public void deleteById(int id) {
        jdbc.update("delete from books where id = :id", Map.of("id", id)); //
    }

    @Override
    public Book getById(long id) {
        try {
            return jdbc.queryForObject("select a.author, b.id as book_id, b.title, g.genre " +
                            "from BOOKS b  left join AUTHORS a on " +
                            "b.author_id = a.id  left join GENRES g on g.id = b.genre_id where b.id = :b.id",
                    Map.of("b.id", id), new BookMapper(authorService, genreService));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Book getByTitle(String title) {
        try {
            return jdbc.queryForObject("select a.author, b.id as book_id, b.title, g.genre " +
                            "from BOOKS b  left join AUTHORS a on " +
                            "b.author_id = a.id  left join GENRES g on g.id = b.genre_id where b.title = :title",
                    Map.of("title", title), new BookMapper(authorService, genreService));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select a.author, b.id as book_id, b.title, g.genre " +
                "from BOOKS b  left join AUTHORS a on " +
                "b.author_id = a.id  left join GENRES g on g.id = b.genre_id", new BookMapper(authorService, genreService));
    }

    private static class BookMapper implements RowMapper<Book> {

        private final AuthorService authorService;
        private final GenreService genreService;

        public BookMapper(AuthorService authorService, GenreService genreService) {
            this.authorService = authorService;
            this.genreService = genreService;
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String genre = resultSet.getString("genre");
            Book book = new Book(title, author, genre);
            book.setId(id);
            return book;
        }
    }
}
