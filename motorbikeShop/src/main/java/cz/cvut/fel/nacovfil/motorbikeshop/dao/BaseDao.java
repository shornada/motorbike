package cz.cvut.fel.nacovfil.motorbikeshop.dao;

import cz.cvut.fel.nacovfil.motorbikeshop.exception.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;


public abstract class BaseDao<T> implements GenericDao<T> {

    protected abstract EntityManager getEntityManager();

    protected final Class<T> type;

    protected BaseDao(Class<T> type) {
        this.type = type;
    }

    @Override
    public T find(Integer id) {
        Objects.requireNonNull(id);
        return getEntityManager().find(type, id);
    }

    public T find(Long id) {
        Objects.requireNonNull(id);
        return getEntityManager().find(type, id);
    }

    @Override
    public List<T> findAll() {
        try {
            return getEntityManager().createQuery("SELECT e FROM " + type.getSimpleName() + " e", type).getResultList();
        } catch (RuntimeException e) {
            throw new EarException(e);
        }
    }

    @Override
    public void persist(T entity) {
        Objects.requireNonNull(entity);
        try {
            getEntityManager().persist(entity);
        } catch (RuntimeException e) {
            throw new EarException(e);
        }
    }

    @Override
    public T update(T entity) {
        Objects.requireNonNull(entity);
        try {
            return getEntityManager().merge(entity);
        } catch (RuntimeException e) {
            throw new EarException(e);
        }
    }

    @Override
    public void remove(T entity) {
        Objects.requireNonNull(entity);
        try {
            final T toRemove = getEntityManager().merge(entity);
            if (toRemove != null) {
                getEntityManager().remove(toRemove);
            }
        } catch (RuntimeException e) {
            throw new EarException(e);
        }
    }

    @Override
    public boolean exists(Integer id) {
        return id != null && getEntityManager().find(type, id) != null;
    }

}
