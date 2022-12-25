package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("authors")
public class Author {

    @Id
    private long id;

    private String authorBook;

    private List<Book> books;

    public Author(String authorBook) {
        this.authorBook = authorBook;
    }

    public Author(long id, String authorBook) {
        this.id = id;
        this.authorBook = authorBook;
    }
}
