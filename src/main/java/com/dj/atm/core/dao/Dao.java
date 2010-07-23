package com.dj.atm.core.dao;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.Entity;

/**
 * All DAOs should implement this interface either directly or indirectly. Also
 * it is assumed that the save method will work for both transient and detached
 * entities.
 */
public interface Dao<K, E extends Entity> {

    /**
     * Save a transient entity or merge a detached entity.
     *
     * @param entity
     *            the entity to be persisted.
     * @return the persisted instance.
     */
    E save(E entity);

    /**
     * Delete an entity.
     *
     * @param entity
     *            the entity to be saved.
     */
    void delete(E entity);

    /**
     * Find the entity using the primary key.
     *
     * @param id
     *            the primary key of the entity.
     * @return the entity
     */
    E findById(K id);
}


//~ Formatted by Jindent --- http://www.jindent.com
