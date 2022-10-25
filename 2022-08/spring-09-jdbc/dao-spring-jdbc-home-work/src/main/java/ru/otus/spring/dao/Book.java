package ru.otus.spring.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
    private int id;
    private final String title;
    private final String author;
    private final String Genre;
}