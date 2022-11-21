package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//@Entity
@Data
@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "authors")
public class Author {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private long id;

//    @Column(name = "author")
    private String author;

//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
//    @JoinColumn(name = "author_id")
    private List<Book> books;

    public Author(long id, String author) {
        this.id = id;
        this.author = author;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Author that = (Author) o;
//
//        if (id != that.id) return false;
//        return author != null ? author.equals(that.author) : that.author == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (author != null ? author.hashCode() : 0);
//        return result;
//    }
}
