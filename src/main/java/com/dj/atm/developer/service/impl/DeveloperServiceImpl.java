package com.dj.atm.developer.service.impl;

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
    public Developer getDeveloper(Long id) {
	return null; // To change body of implemented methods use File |
	// Settings | File Templates.
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
