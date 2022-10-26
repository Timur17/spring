package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.BookGenre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
        return jdbc.queryForObject("select id, genre from GENRES where id = :id",
                Map.of("id", id), new GenreMapper());
    }


    @Override
    public List<BookGenre> getAll() {
        return jdbc.query("select id, genre from GENRES", new GenreMapper());
    }

    public static class GenreMapper implements RowMapper<BookGenre> {

        @Override
        public BookGenre mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String genre = rs.getString("genre");
            BookGenre bookGenre = new BookGenre(genre);
            bookGenre.setId(id);
            return bookGenre;
        }
    }
}


