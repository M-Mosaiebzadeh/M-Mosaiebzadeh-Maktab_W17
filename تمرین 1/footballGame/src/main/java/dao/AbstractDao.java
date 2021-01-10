package dao;

import entities.IEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

public abstract class AbstractDao<K,E extends IEntity<K>> implements DataAccess<K,E>{
    protected EntityManager entityManager;

    public AbstractDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(E entity) {
        entityManager.persist(entity);
    }

    public E load(K id) {
        return entityManager.find(getEntityClass(),id);
    }

    public void delete(E entity) {
        entityManager.remove(entity);
    }


    public void update(E entity) {
        entityManager.merge(entity);
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    public abstract Class<E> getEntityClass();

}
