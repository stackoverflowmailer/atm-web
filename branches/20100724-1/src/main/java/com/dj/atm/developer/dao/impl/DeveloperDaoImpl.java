package com.dj.atm.developer.dao.impl;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.dao.impl.AbstractJpaDaoImpl;
import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.developer.dao.DeveloperDao;
import com.dj.atm.developer.model.Developer;
import com.dj.atm.developer.model.Name;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

import javax.persistence.Query;

/**
 * @author Script Runner
 * @since 0.0.1
 */
public class DeveloperDaoImpl extends AbstractJpaDaoImpl<Long, Developer> implements DeveloperDao {

    /**
     * Get all the developers in the team regardless
     * of any condition.
     * <p/>
     * The results are paginated to improve
     * load / performance at the server and the client.
     *
     * @return list of all developers.
     */
    public List<Developer> getDevelopers(QueryParameter qp) {
        final Query query = entityManager.get().createNamedQuery("getAllDevelopers");

        query.setFirstResult(qp.getOffset());
        query.setMaxResults(qp.getLimit());

        List<Developer> developers = query.getResultList();

        return developers;
    }

    /**
     * Get all the developers matching the supplied name parameter.
     *
     * @param name - the search key.
     * @return the list of all developers with the matching names.
     */
    @Override
    public List<Developer> getDevelopersByName(Name name) {
        final Query                                    query         =
            entityManager.get().createNamedQuery("getDeveloperByName");
        @SuppressWarnings("unchecked") List<Developer> developerList = query.getResultList();

        return developerList;
    }
}



