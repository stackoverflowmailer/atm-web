package com.dj.atm.user.dao.impl;

import com.dj.atm.core.dao.impl.AbstractJpaDaoImpl;
import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.model.User;
import com.dj.atm.user.dao.UserDao;

import javax.persistence.Query;
import java.util.List;

/**
 * @author ScriptRunner
 */
public class UserDaoImpl extends AbstractJpaDaoImpl<Long, User> implements UserDao {
    /**
     * Get all the users in the system regardless
     * of any condition.
     * <p/>
     * Implementation should preferably paginate results to improve
     * load / performance at the server and the client.
     *
     * @param qp parameter object for this query encapsulating limit and offset.
     * @return list of all users.
     */
    @Override
    public List<User> getDevelopers(QueryParameter qp) {
        final Query query = entityManager.get().createNamedQuery("getAllUsers");
        query.setFirstResult(qp.getOffset());
        query.setMaxResults(qp.getLimit());
        List<User> users = query.getResultList();
        return users;
    }
}
