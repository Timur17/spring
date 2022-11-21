package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "genres")


public class Genre {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Column(name = "genre")
    private String genre;

    //    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
//    @JoinColumn(name = "genre_id")
    private List<Book> books;

    public Genre(long id, String genre) {
        this.id = id;
        this.genre = genre;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Genre genre = (Genre) o;
//
//        if (id != genre.id) return false;
//        return this.genre != null ? this.genre.equals(genre.genre) : genre.genre == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (genre != null ? genre.hashCode() : 0);
//        return result;
//    }
}
