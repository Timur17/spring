package ru.otus.spring.domain;

import lombok.Data;

import java.util.List;

@Data
public class BookAuthor {
    private int id;
    private final String author;
    private List<Book> books;
}
