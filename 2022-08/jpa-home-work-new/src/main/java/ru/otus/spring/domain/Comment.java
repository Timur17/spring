package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Document("comments")
public class Comment {

    @Id
    private String id;

    private String commentBook;

    private Book book;

    public Comment(String commentBook) {
        this.commentBook = commentBook;
    }

    public Comment(String id, String commentBook) {
        this.id = id;
        this.commentBook = commentBook;
    }

    public Comment(String commentBook, Book book) {
        this.commentBook = commentBook;
        this.book = book;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + commentBook + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getCommentBook() {
        return commentBook;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCommentBook(String commentBook) {
        this.commentBook = commentBook;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
