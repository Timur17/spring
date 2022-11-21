package ru.otus.spring.repositories;//package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public CommentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public long count() {
        Query query = em.createQuery("select count(c) from Comment c");
        return (long) query.getSingleResult();
    }

    @Override
    public Comment insert(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }


    @Override
    public void insert(Comment comment, long bookId) {
        Query query = em.createQuery(" insert Comment c set c.comment = :comment, c.book_id = :book_id ");
        query.setParameter("comment", comment.getComment());
        query.setParameter("book_id", bookId);
        query.executeUpdate();
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> getAllByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c " +
                "from Comment c " +
                "where c.bookId = :book_id ", Comment.class);
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }


    @Override
    public void updateById(Comment comment, long id) {
        Query query = em.createQuery(" update Comment c set c.comment = :comment " +
                " where c.id = :id");
        query.setParameter("comment", comment.getComment());
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        getById(id).ifPresent(em::remove);
    }
}
