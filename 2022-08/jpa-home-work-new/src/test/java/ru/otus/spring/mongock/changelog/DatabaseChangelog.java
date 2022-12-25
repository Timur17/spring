package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepository;


@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "stvort", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }
//
//    @ChangeSet(order = "002", id = "insertLermontov", author = "ydvorzhetskiy")
//    public void insertLermontov(MongoDatabase db) {
//        MongoCollection<Document> myCollection = db.getCollection("persons");
//        var doc = new Document().append("name", "Lermontov");
//        myCollection.insertOne(doc);
//    }
//
    @ChangeSet(order = "002", id = "insertBook", author = "tukhtarov")
    public void insertPushkin(BookRepository repository) {
        repository.save(new Book("1", "war and peace", new Author("Tolstoy"), new Genre("Historical novel")));
    }
}
