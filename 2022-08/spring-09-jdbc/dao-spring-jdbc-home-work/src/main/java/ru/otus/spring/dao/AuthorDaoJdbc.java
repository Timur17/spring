package ru.otus.spring.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookAuthor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Objects.isNull;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;


    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
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
        return id;
    }

    @Override
    public void updateById(BookAuthor bookAuthor, int id) {
        jdbc.update("update AUTHORS set author = :author where id = :id",
                Map.of("id", id, "author", bookAuthor.getAuthor()));
    }

    @Override
    public void deleteById(int id) {
        jdbc.update("delete from AUTHORS where id = :id", Map.of("id", id));
    }

    @Override
    public BookAuthor getByAuthor(String author) {
        Map<Long, BookAuthor> authors = jdbc.query("select a.id as a_id, a.author, b.id as book_id, b.title, g.genre " +
                        "from AUTHORS a  left join BOOKS b on " +
                        "a.id = b.author_id left join GENRES g on g.id = b.genre_id where a.author = :a.author",
                Map.of("a.author", author), new AuthorResultSetExtractor());
        if (authors.size() == 0)
            return null;
        return new ArrayList<>(Objects.requireNonNull(authors).values()).get(0);
    }

    @Override
    public List<BookAuthor> getAll() {
        Map<Long, BookAuthor> authors = jdbc.query("select a.id as a_id, a.author, b.id as book_id, b.title, g.genre " +
                "from  AUTHORS a left join BOOKS b on " +
                "a.id = b.author_id  left join GENRES g on g.id = b.genre_id", new AuthorResultSetExtractor());
        if (authors.size() == 0)
            return null;
        return new ArrayList<>(Objects.requireNonNull(authors).values());
    }

    @Override
    public BookAuthor getById(long id) {
        Map<Long, BookAuthor> authors = jdbc.query("select a.id as a_id, a.author, b.id as book_id, b.title, g.genre " +
                        "from  AUTHORS a left join BOOKS b on " +
                        "a.id = b.author_id left join GENRES g on g.id = b.genre_id where a.id = :a.id",
                Map.of("a.id", id), new AuthorResultSetExtractor());
        if (authors.size() == 0)
            return null;
        return new ArrayList<>(Objects.requireNonNull(authors).values()).get(0);
    }

    public class AuthorResultSetExtractor implements
            ResultSetExtractor<Map<Long, BookAuthor>> {
        @Override
        public Map<Long, BookAuthor> extractData(ResultSet rs) throws SQLException,
                DataAccessException {

            Map<Long, BookAuthor> authorMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("a_id");
                String author = rs.getString("author");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                int bookId = rs.getInt("book_id");

                BookAuthor bookAuthor = authorMap.get(id);
                if (bookAuthor == null) {
                    bookAuthor = new BookAuthor(author);
                    bookAuthor.setId(id.intValue());
                    bookAuthor.setBooks(new ArrayList<>());
                    authorMap.put(id, bookAuthor);
                }
                Book book = new Book(title, author, genre);
                book.setId(bookId);
                bookAuthor.getBooks().add(book);
            }
            return authorMap;
        }
    }


}
