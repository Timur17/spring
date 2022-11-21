package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genres")
@NamedEntityGraph(name = "genres-books-entity-graph", attributeNodes = {@NamedAttributeNode("books")})
public class Genre {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(name = "genre")
    private String genre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "genre_id")
    private List<Book> books;

    public Genre(String genre) {
        this.genre = genre;
    }
}
