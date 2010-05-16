package com.dj.atm.core.dao.impl;

import com.dj.atm.core.dao.Dao;
import com.dj.atm.core.model.Entity;
import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

/**
 * Super class for all applications DAOs.
 *
 * @author Deepak Jacob
 */
public abstract class AbstractJpaDaoImpl<K, E extends Entity> implements
        Dao<K, E> {

    protected Class<E> entityClass;

    @Inject
    protected Provider<EntityManager> entityManager;

    public AbstractJpaDaoImpl() {
        ParameterizedType genericType = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericType.getActualTypeArguments()[1];

    }

    @Override
    public E save(E entity) {
        if (entity.getId() == null) {
            entityManager.get().persist(entity);
        } else {
            entityManager.get().merge(entity);
        }
        return entity;
    }

    @Override
    public void delete(E entity) {
        entityManager.get().remove(entity);
    }

    @Override
    public E findById(K id) {
        return entityManager.get().find(entityClass, id);
    }
}
