package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Person;

import java.security.spec.NamedParameterSpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDaoJdbc implements PersonDao {

    private final NamedParameterJdbcOperations jdbc;

    public PersonDaoJdbc(NamedParameterJdbcOperations jdbcOperations)
    {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int count() {
        Integer count = jdbc.getJdbcOperations().
                queryForObject("select count(*) from persons", Integer.class);
        return count == null? 0: count;
    }

    @Override
    public void insert(Person person) {
        jdbc.update("insert into persons (id, name) values (:id, :name)",
                Map.of("id", person.getId(), "name", person.getName()));
    }

    @Override
    public Person getById(long id) {
        return jdbc.queryForObject("select id, name from persons where id = :id",
                Map.of("id", id)
                ,new PersonMapper());
    }

    @Override
    public List<Person> getAll() {
        return jdbc.query("select id, name from persons", new PersonMapper());
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from persons where id = :id",
                Map.of("id", id));
    }


    private static class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Person(id, name);
        }
    }
}
