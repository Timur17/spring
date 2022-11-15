package ru.otus.spring.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private final String title;
    @Column(name = "author")
    private final String author;
    @Column(name = "genre")
    private final String genre;
}