package ru.otus.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;

import java.sql.SQLException;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class DemoApplication {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(DemoApplication.class, args);
    }

}
