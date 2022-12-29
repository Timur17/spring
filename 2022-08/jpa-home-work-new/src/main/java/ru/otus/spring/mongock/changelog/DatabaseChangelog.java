package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;
import ru.otus.spring.repositories.GenreRepository;


@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "stvort", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertBook", author = "tukhtarov")
    public void insertPushkin(BookRepository repository) {
        repository.save(new Book("1", "war and peace", new Author("Tolstoy"), new Genre("Historical novel")));
    }

    @ChangeSet(order = "003", id = "insertAuthor", author = "tukhtarov")
    public void insertAuthor(AuthorRepository repository) {
        repository.save(new Author("1", "Tolstoy"));
    }

    @ChangeSet(order = "004", id = "insertGenre", author = "tukhtarov")
    public void insertGenre(GenreRepository repository) {
        repository.save(new Genre("1", "Historical novel"));
    }

    @ChangeSet(order = "005", id = "insertComment", author = "tukhtarov")
    public void insertComment(CommentRepository repository) {
        repository.save(new Comment("1", "The best book", "1"));
    }
}
