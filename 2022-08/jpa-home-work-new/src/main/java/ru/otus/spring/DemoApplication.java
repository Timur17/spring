package ru.otus.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(DemoApplication.class, args);
//		Console.main(args);
		System.out.printf("Чтобы проверить библиотеку открывай: %n%s%n%s%n%s%n",
				"http://localhost:8080",
				"http://localhost:8080/add?",
				"http://localhost:8080/edit?id=1");
	}

}
