package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@Document("authors")
public class Author {

    @Id
    private String id;

    private String authorBook;

    public Author(String authorBook) {
        this.authorBook = authorBook;
    }

    public Author(String id, String authorBook) {
        this.id = id;
        this.authorBook = authorBook;
    }
}
