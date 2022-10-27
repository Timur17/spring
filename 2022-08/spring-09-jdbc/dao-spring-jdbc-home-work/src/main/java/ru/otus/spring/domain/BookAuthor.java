package ru.otus.spring.domain;

import lombok.Data;


@Data
public class BookAuthor {
    private int id;
    private final String author;
}
