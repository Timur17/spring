//package ru.otus.spring.repositories;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.otus.spring.domain.Book;
//
//import javax.persistence.*;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@Transactional
//public class BookRepositoryJpa implements BookRepository{
//
//    @PersistenceContext
//    private final EntityManager em;
//
//    public BookRepositoryJpa(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public long count() {
//        Query query = em.createQuery("select count(b) from Book b");
//        return (long) query.getSingleResult();
//    }
//
//    @Override
//    public Book insert(Book book) {
//        if (book.getId() == 0) {
//            em.persist(book);
//            return book;
//        } else {
//            return em.merge(book);
//        }
//    }
//
//    @Override
//    public Optional<Book> getById(long id) {
//        return Optional.ofNullable(em.find(Book.class, id));
//    }
//
//    @Override
//    public List<Book> getAll() {
//        return null;
//    }
//
//    @Override
//    public Book getByTitle(String title) {
//        TypedQuery<Book> query = em.createQuery("select b from Book b where b.title = :title", Book.class);
//        query.setParameter("title", title);
//        return query.getSingleResult();
//    }
//
//    @Override
//    public void updateById(Book book, long id) {
//        Query query = em.createQuery(" update Book b set b.title = :title, b.author = :author, b.genre = :genre " +
//                " where b.id = :id" );
//        query.setParameter("title", book.getTitle());
//        query.setParameter("author", book.getAuthor());
//        query.setParameter("genre", book.getGenre());
//        query.setParameter("id", id);
//        query.executeUpdate();
//    }
//
//    @Override
//    public void deleteById(long id) {
//        Query query = em.createQuery("delete " +
//                "from Book s " +
//                "where s.id = :id");
//        query.setParameter("id", id);
//        query.executeUpdate();
//    }
//}
