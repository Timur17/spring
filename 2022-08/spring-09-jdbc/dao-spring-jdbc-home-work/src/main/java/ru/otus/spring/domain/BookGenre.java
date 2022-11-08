package ru.otus.spring.domain;

import lombok.Data;

import java.util.List;

@Data
public class BookGenre {
    private int id;
    private final String genre;
    private List<Book> books;
}
