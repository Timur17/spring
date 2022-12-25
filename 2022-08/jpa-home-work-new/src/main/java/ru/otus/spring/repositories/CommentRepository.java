package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.Comment;

import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, Long> {

    Optional<Comment> findById(String id);

    void deleteById(String id);

}
