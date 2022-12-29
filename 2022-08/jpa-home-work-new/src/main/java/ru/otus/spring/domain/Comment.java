package ru.otus.spring.domain;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Document("comments")
public class Comment {

    @Id
    private String id;

    private String commentBook;

    private String bookId;

    public Comment(String commentBook, String bookId) {
        this.commentBook = commentBook;
        this.bookId = bookId;
    }

    public Comment(String id, String commentBook, String bookId) {
        this.id = id;
        this.commentBook = commentBook;
        this.bookId = bookId;
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

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentBook='" + commentBook + '\'' +
                ", bookId='" + bookId + '\'' +
                '}';
    }
}
