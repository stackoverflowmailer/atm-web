package com.dj.atm.developer.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.dj.atm.core.dao.impl.AbstractJpaDaoImpl;
import com.dj.atm.developer.dao.DeveloperDao;
import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.model.Name;

/**
 * @author Deepak Jacob
 */
public class DeveloperDaoImpl extends AbstractJpaDaoImpl<Long, Developer>
	implements DeveloperDao {
    /**
     * Get all the developers matching the supplied name parameter.
     * 
     * @param name
     *            - the search key.
     * @return the list of all developers with the matching names.
     */
    @Override
    public List<Developer> getDevelopersByName(Name name) {
	final Query query = entityManager.get().createNamedQuery("getDeveloperByName");
	@SuppressWarnings("unchecked")
	List<Developer> developerList = query.getResultList();
	return developerList;
    }
}
