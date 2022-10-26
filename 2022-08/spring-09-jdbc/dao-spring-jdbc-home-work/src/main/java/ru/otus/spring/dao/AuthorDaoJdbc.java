package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    private final BookDaoJdbc bookDaoJdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcOperations, BookDaoJdbc bookDaoJdbc) {
        this.jdbc = jdbcOperations;
        this.bookDaoJdbc = bookDaoJdbc;
    }

    @Override
    public int count() {
        Integer amount = jdbc.getJdbcOperations().queryForObject("select count(*) from AUTHORS", Integer.class);
        return isNull(amount) ? 0 : amount;
    }

    @Override
    public int insert(BookAuthor bookAuthor) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", bookAuthor.getAuthor());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("INSERT INTO AUTHORS (author) values (:author)",
                params, keyHolder);
        int id = isNull(keyHolder.getKey()) ? 0 : keyHolder.getKey().intValue();
        bookAuthor.setId(id);
//        for (Book book :bookAuthor.getBooks()) {
//            bookDaoJdbc.insert(book);
//        }
        return id;
    }

    @Override
    public void updateById(BookAuthor bookAuthor, int id) {
        jdbc.update("update AUTHORS set author = :author where id = :id",
                Map.of("id", id, "author", bookAuthor.getAuthor()));
//        for (Book book :bookAuthor.getBooks()) {
//            bookDaoJdbc.updateById(book, book.getId());
//        }
    }

    @Override
    public void deleteById(int id) {
        jdbc.update("delete from AUTHORS where id = :id", Map.of("id", id));
    }

    @Override
    public BookAuthor getById(long id) {
        BookAuthor bookAuthor =  jdbc.queryForObject("select id, author from AUTHORS where id = :id",
                Map.of("id", id), new AuthorMapper());
//        bookAuthor.setBooks(bookDaoJdbc.getByAuthor(bookAuthor.getAuthor()));
        return bookAuthor;
    }

    @Override
    public List<BookAuthor> getAll() {
        return jdbc.query("select id, author from AUTHORS", new AuthorMapper());
    }

    public static class AuthorMapper implements RowMapper<BookAuthor> {

        @Override
        public BookAuthor mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String author = rs.getString("author");
            BookAuthor bookAuthor = new BookAuthor(author);
            bookAuthor.setId(id);
            return bookAuthor;
        }
    }

}
