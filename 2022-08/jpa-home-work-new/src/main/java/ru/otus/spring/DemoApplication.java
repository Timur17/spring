package ru.otus.spring;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
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
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		CommentRepository repository = context.getBean(CommentRepository.class);
		BookRepository bookRep = context.getBean(BookRepository.class);


		bookRep.save(new Book("newBook"));

		System.out.println(bookRep.findAll());

//		repository.save(new Comment(1, "first"));
//
//		System.out.println(repository.findAll());

//		SpringApplication.run(DemoApplication.class, args);
//		Console.main(args);
	}

}
