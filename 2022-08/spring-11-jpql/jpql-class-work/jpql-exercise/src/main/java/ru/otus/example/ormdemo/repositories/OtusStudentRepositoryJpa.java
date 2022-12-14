package ru.otus.example.ormdemo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.example.ormdemo.models.OtusStudent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

// @Transactional должна стоять на методе сервиса.
// Причем, если метод не подразумевает изменения данных в БД то категорически желательно
// выставить у аннотации параметр readOnly в true.
// Но это только упражнение и транзакции мы пока не проходили.
// Поэтому, для упрощения, пока вешаем над классом репозитория
@Transactional
@Repository
public class OtusStudentRepositoryJpa implements OtusStudentRepository {

    @PersistenceContext
    private final EntityManager em;

    public OtusStudentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public OtusStudent save(OtusStudent student) {
        if (student.getId() == 00) {
            em.persist(student);
            return student;
        }
        return em.merge(student);
    }

    @Override
    public Optional<OtusStudent> findById(long id) {
//        TypedQuery<OtusStudent> query = em.createQuery("select s from OtusStudent s where s.id = :id", OtusStudent.class);
//        query.setParameter("id", id);
//        return Optional.ofNullable(query.getSingleResult());
        return Optional.ofNullable(em.find(OtusStudent.class, id));
    }

    @Override
    public List<OtusStudent> findAll() {
        TypedQuery<OtusStudent> query = em.createQuery("select s from OtusStudent s", OtusStudent.class);
        return query.getResultList();
    }

    @Override
    public List<OtusStudent> findByName(String name) {
        TypedQuery<OtusStudent> query = em.createQuery("select s from OtusStudent s where s.name = :name", OtusStudent.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update OtusStudent s " +
                "set s.name = :name " +
                "where s.id = :id ");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query =  em.createQuery("delete OtusStudent s where s.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
