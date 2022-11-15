package ru.otus.spring.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Table(name = "author")
public class BookAuthor {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "author")
    private final String author;
    @Column(name = "books")
    private List<Book> books;
}
