package com.dj.atm.user.dao;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.dao.Dao;
import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.model.User;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

/**
 * @author ScriptRunner
 */
public interface UserDao extends Dao<Long, User> {

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
    public List<User> getDevelopers(QueryParameter qp);
}


//~ Formatted by Jindent --- http://www.jindent.com
