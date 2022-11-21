//package ru.otus.spring.repositories;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.otus.spring.domain.Author;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@Transactional
//public class AuthorRepositoryJpa implements AuthorRepository {
//
//    @PersistenceContext
//    private final EntityManager em;
//
//    public AuthorRepositoryJpa(EntityManager em) {
//        this.em = em;
//    }
//
//
//    @Override
//    public long count() {
//        Query query = em.createQuery("select count(a) from BookAuthor a");
//        return (long) query.getSingleResult();
//    }
//
//    @Override
//    public Author insert(Author author) {
//        if (author.getId() == 0) {
//            em.persist(author);
//            return author;
//        } else {
//            return em.merge(author);
//        }
//    }
//
//    @Override
//    public void updateById(Author author, int id) {
//        Query query = em.createQuery(" update BookAuthor a set a.author = :author" +
//                " where a.id = :id" );
//        query.setParameter("author", author.getAuthor());
//        query.setParameter("id", id);
//        query.executeUpdate();
//    }
//
//    @Override
//    public void deleteById(int id) {
//
//    }
//
//    @Override
//    public Optional<Author> getById(long id) {
//        return Optional.ofNullable(em.find(Author.class, id));
//    }
//
//    @Override
//    public Optional<Author> getByAuthor(String author) {
//        TypedQuery<Author> query = em.createQuery("select a from BookAuthor a where a.author = :author", Author.class);
//        query.setParameter("author", author);
//        return Optional.ofNullable(query.getSingleResult());
//    }
//
//    @Override
//    public List<Author> getAll() {
//        return null;
//    }
//}
