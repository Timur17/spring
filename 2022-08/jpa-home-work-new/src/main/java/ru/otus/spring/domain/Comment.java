package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Comments")
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "book_id")
    private long bookId;

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(String comment, long bookId) {
        this.comment = comment;
        this.bookId = bookId;
    }
}
