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
@Document
public class Genre {
    @Id
    private long id;

    private String genreBook;

    private List<Book> books;

    public Genre(String genreBook) {
        this.genreBook = genreBook;
    }

    public Genre(long id, String genreBook) {
        this.id = id;
        this.genreBook = genreBook;
    }
}
