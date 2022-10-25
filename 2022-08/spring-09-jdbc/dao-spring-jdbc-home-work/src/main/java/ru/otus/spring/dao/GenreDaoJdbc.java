package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDaoJdbc {

    private final JdbcOperations jdbc;

    public GenreDaoJdbc(JdbcOperations jdbcOperations) {
        this.jdbc = jdbcOperations;
    }
}


