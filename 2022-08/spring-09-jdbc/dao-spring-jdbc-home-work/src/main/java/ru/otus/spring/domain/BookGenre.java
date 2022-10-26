package ru.otus.spring.domain;

import lombok.Data;

@Data
public class BookGenre {
    private int id;
    private final String genre;
}
