package ru.otus.spring.dao;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        Integer amount = jdbc.getJdbcOperations().queryForObject("select count(*) from books", Integer.class);
        return isNull(amount) ? 0 : amount;
    }

    @Override
    public int insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("author", book.getAuthor());
        params.addValue("genre", book.getGenre());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("INSERT INTO books (title, author, genre) values (:title, :author, :genre)",
                params, keyHolder);
        int id = isNull(keyHolder.getKey()) ? 0 : keyHolder.getKey().intValue();
        book.setId(id);
        return id;
    }

    @Override
    public void updateById(Book book, int id) {
        jdbc.update("update books set title = :title, author = :author, genre = :genre where id = :id",
                Map.of("id", id, "title", book.getTitle(),
                        "author", book.getAuthor(), "genre", book.getGenre()));
    }

    @Override
    public void deleteById(int id) {
        jdbc.update("delete from books where id = :id", Map.of("id", id));
    }

    @Override
    public Book getById(long id) {
        return jdbc.queryForObject("select id, title, author, genre from books where id = :id",
                Map.of("id", id), new BookMapper());
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return jdbc.query("select id, title, author, genre from books where author = :author",
                Map.of("author", author), new BookMapper());
    }

    @Override
    public List<Book> getByGenre(String genre) {
        return jdbc.query("select id, title, author, genre from books where genre = :genre",
                Map.of("genre", genre), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, title, author, genre from books", new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String genre = resultSet.getString("genre");
            Book book = new Book(title, author, genre);
            book.setId(id);
            return book;
        }
    }
}
