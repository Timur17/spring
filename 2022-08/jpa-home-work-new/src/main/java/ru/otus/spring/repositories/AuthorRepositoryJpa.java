package ru.otus.spring.repositories;//package ru.otus.spring.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;


@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }


    @Override
    public long count() {
        Query query = em.createQuery("select count(a) from Author a");
        return (long) query.getSingleResult();
    }

    @Override
    public Author insert(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public void updateById(Author author, long id) {
        Query query = em.createQuery(" update Author a set a.author = :author" +
                " where a.id = :id");
        query.setParameter("author", author.getAuthor());
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        getById(id).ifPresent(em::remove);
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Optional<Author> getByAuthor(String author) {
        TypedQuery<Author> query = em.createQuery("select a " +
                "from Author a " +
                "where a.author = :author", Author.class);
        query.setParameter("author", author);
        try {
            return Optional.ofNullable(query.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }

    }

    @Override
    public List<Author> getAll() {
        EntityGraph<?> graph = em.getEntityGraph("authors-books-entity-graph");
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        query.setHint(FETCH.getKey(), graph);
        return query.getResultList();
    }
}
