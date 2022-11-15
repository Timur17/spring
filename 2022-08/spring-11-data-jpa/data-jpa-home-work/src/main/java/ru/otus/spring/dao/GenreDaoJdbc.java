package ru.otus.spring.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Objects.isNull;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        Integer amount = jdbc.getJdbcOperations().queryForObject("select count(*) from GENRES", Integer.class);
        return isNull(amount) ? 0 : amount;
    }

    @Override
    public int insert(BookGenre bookGenre) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("genre", bookGenre.getGenre());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("INSERT INTO GENRES (genre) values (:genre)",
                params, keyHolder);
        int id = isNull(keyHolder.getKey()) ? 0 : keyHolder.getKey().intValue();
        bookGenre.setId(id);
        return id;
    }

    @Override
    public void updateById(BookGenre bookGenre, int id) {
        jdbc.update("update GENRES set genre = :genre where id = :id",
                Map.of("id", id,
                        "genre", bookGenre.getGenre()));
    }

    @Override
    public void deleteById(int id) {
        jdbc.update("delete from GENRES where id = :id", Map.of("id", id));
    }


    @Override
    public BookGenre getById(long id) {
        Map<Long, BookGenre> authors = jdbc.query("select g.id as g_id, b.id as book_id, a.author, b.title, g.genre " +
                        "from GENRES g left join BOOKS b on " +
                        "g.id = b.genre_id left join AUTHORS a on a.id = b.author_id where g.id = :g.id",
                Map.of("g.id", id), new GenreResultSetExtractor());
        if (authors.size() == 0)
            return null;
        return new ArrayList<>(Objects.requireNonNull(authors).values()).get(0);
    }


    @Override
    public BookGenre getByGenre(String genre) {
        Map<Long, BookGenre> genreMap = jdbc.query("select g.id as g_id, b.id as book_id, a.author, b.title, g.genre " +
                        "from GENRES g left join BOOKS b on " +
                        "g.id = b.genre_id left join AUTHORS a on a.id = b.author_id where g.genre = :g.genre",
                Map.of("g.genre", genre), new GenreResultSetExtractor());
        if (genreMap.size() == 0)
            return null;
        return new ArrayList<>(Objects.requireNonNull(genreMap).values()).get(0);
    }

    @Override
    public List<BookGenre> getAll() {
        Map<Long, BookGenre> genreMap = jdbc.query("select g.id as g_id, b.id as book_id, a.author, b.title, g.genre " +
                        "from GENRES g left join BOOKS b on " +
                        "g.id = b.genre_id left join AUTHORS a on a.id = b.author_id",
                new GenreResultSetExtractor());
        if (genreMap.size() == 0)
            return null;
        return new ArrayList<>(Objects.requireNonNull(genreMap).values());
    }


    public class GenreResultSetExtractor implements
            ResultSetExtractor<Map<Long, BookGenre>> {
        @Override
        public Map<Long, BookGenre> extractData(ResultSet rs) throws SQLException,
                DataAccessException {

            Map<Long, BookGenre> authorMap = new HashMap<>();
            while (rs.next()) {
                Long id = rs.getLong("g_id");
                String author = rs.getString("author");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                int bookId = rs.getInt("book_id");

                BookGenre bookGenre = authorMap.get(id);
                if (bookGenre == null) {
                    bookGenre = new BookGenre(genre);
                    bookGenre.setId(id.intValue());
                    bookGenre.setBooks(new ArrayList<>());
                    authorMap.put(id, bookGenre);
                }
                Book book = new Book(title, author, genre);
                book.setId(bookId);
                bookGenre.getBooks().add(book);
            }
            return authorMap;
        }
    }
}


