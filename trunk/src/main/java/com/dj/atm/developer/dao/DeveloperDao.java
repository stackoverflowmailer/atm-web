package com.dj.atm.developer.dao;

import java.util.List;

import com.dj.atm.core.dao.Dao;
import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.model.Name;

/**
 * @author Deepak Jacob
 */
public interface DeveloperDao extends Dao<Long, Developer> {
    public List<Developer> getDevelopersByName(Name name);

}
