package ru.otus.spring.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Book;


@Data
@AllArgsConstructor
public class BookDto {

    private long id;

    private String title;

    private String author;

    private String genre;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getAuthorBook(), book.getGenre().getGenreBook());
    }

}