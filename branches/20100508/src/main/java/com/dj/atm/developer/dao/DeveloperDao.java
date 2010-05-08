package com.dj.atm.developer.dao;

import java.util.List;

import com.dj.atm.core.dao.Dao;
import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.model.Name;

/**
 * @author Script Runner
 * @since 0.0.1
 */
public interface DeveloperDao extends Dao<Long, Developer> {
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
    public List<Developer> getDevelopers(QueryParameter qp);

    public List<Developer> getDevelopersByName(Name name);

}
