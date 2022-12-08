package DAO;


import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {
    @PersistenceContext
    EntityManager entityManager;

//    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("shots");
//    static EntityManager entityManager = emf.createEntityManager();
//    @PersistenceUnit
//    private EntityManagerFactory managerFactory;

    private final Class<T> clazz;
    private List<T> hits;

    protected AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @Transactional
    public void clear() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> delete = criteriaBuilder.createCriteriaDelete(clazz);
        delete.from(clazz);
        entityManager.createQuery(delete).executeUpdate();
        System.out.println("c");
    }

    @Override
    @Transactional
    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        query.select(query.from(clazz));
        hits = entityManager.createQuery(query).getResultList();
//        System.out.println("g All");
        return hits;
    }

    @Override
    @Transactional
    public void addShot(T entity) {
        entityManager.persist(entity);
        System.out.println("n add");
    }

    @Override
    public boolean isEmpty() {
        List<T> list = getAll();
        return list.isEmpty();
    }
}
