package ru.otus.spring.domain;

import lombok.Data;

@Data
public class Book {
    private int id;
    private final String title;
    private final String author;
    private final String genre;
}