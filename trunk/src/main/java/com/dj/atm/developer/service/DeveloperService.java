package com.dj.atm.developer.service;

import java.util.List;

import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.model.Name;

/**
 * Service class for managing Developers
 * 
 * @author Deepak Jacob
 */
public interface DeveloperService {

    /**
     * Return the developer matching the provided id;
     * 
     * @param id
     *            - used as the search key.
     * @return the developer matching the search key, if no developer is found
     *         with the matching key, then return null
     */
    public Developer getDeveloper(Long id);

    /**
     * Get all developers matching the supplied <code>Name</code> criteria. Note
     * that if attributes of the Name contain patterns then a substring matching
     * should be done.
     * 
     * @param name
     *            the pattern to be searched for.
     * @return the list of developers having matching names.
     */
    public List<Developer> getDevelopersByName(Name name);

    /**
     * Persist the developer and return the id.
     * 
     * @param developer
     *            the developer entity to be saved.
     * @return the developer instance that just got persisted.
     */
    public Developer save(Developer developer);

    /**
     * Removes the developer from the team
     * 
     * @param developer
     *            the developer who should be removed from the team.
     */
    public Developer remove(Developer developer);
}
