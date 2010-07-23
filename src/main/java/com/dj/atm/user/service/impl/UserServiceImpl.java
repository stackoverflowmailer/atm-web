package com.dj.atm.user.service.impl;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.model.User;
import com.dj.atm.core.service.HashingService;
import com.dj.atm.user.dao.UserDao;
import com.dj.atm.user.service.UserService;

import com.google.inject.Inject;

import com.wideplay.warp.persist.Transactional;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;
import java.util.List;

/**
 * @author ScriptRunner
 */
public class UserServiceImpl implements UserService {
    @Inject
    private HashingService hashingService;
    private UserDao        userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public HashingService getHashingService() {
        return hashingService;
    }

    public void setHashingService(HashingService hashingService) {
        this.hashingService = hashingService;
    }

    /**
     * Persists the user.
     * @param u
     * @return
     */
    @Override
    @Transactional
    public User saveUser(User u) {
        String hash = u.getPassword();

        // u.setPasswordHash(hash);
        u.setPassword(hash);
        u.setCreationDate(new Date());

        /**
         * TODO:When sending User object to front-end hash should NOT be send.
         */
        return userDao.save(u);
    }

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
    @Transactional
    public List<User> getUsers(QueryParameter qp) {
        return getUserDao().getDevelopers(qp);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
