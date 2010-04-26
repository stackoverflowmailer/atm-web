package com.dj.atm.developer.service.impl;

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.developer.dao.DeveloperDao;
import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.model.Name;
import com.dj.atm.developer.service.DeveloperService;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

import java.util.List;

/**
 * @author Deepak Jacob
 */
public class DeveloperServiceImpl implements DeveloperService {

    @Inject
    private DeveloperDao developerDao;

    public DeveloperDao getDeveloperDao() {
        return developerDao;
    }

    public void setDeveloperDao(DeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    @Override
    @Transactional
    public Developer getDeveloper(Long id) {
        return getDeveloperDao().findById(id);
    }

    /**
     * Get all the developers in the team regardless
     * of any condition.
     * <p/>
     * Implementation should preferably paginate results to improve
     * load / performance at the server and the client.
     *
     * @param qp parameter object for this query encapsulating limit and offset.
     * @return list of all developers.
     */
    @Transactional
    public List<Developer> getDevelopers(QueryParameter qp) {
        return getDeveloperDao().getDevelopers(qp);
    }

    @Override
    public List<Developer> getDevelopersByName(Name name) {
        return getDeveloperDao().getDevelopersByName(name);
    }

    @Override
    @Transactional
    public Developer save(Developer developer) {
        return getDeveloperDao().save(developer);
    }

    @Override
    public Developer remove(Developer developer) {
        return null; // To change body of implemented methods use File |
        // Settings | File Templates.
    }
}
