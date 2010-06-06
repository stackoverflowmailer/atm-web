package com.dj.atm.user.service;

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.model.User;

import java.util.List;

/**
 * @author ScriptRunner
 */
public interface UserService {

    /**
     * Persists the user entity to the data store.
     *
     * @param u the user instance to e saved.
     * @return the persisted entity
     */
    public User saveUser(User u);

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
    public List<User> getUsers(QueryParameter qp);
}
