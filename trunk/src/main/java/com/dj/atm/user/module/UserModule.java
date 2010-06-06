package com.dj.atm.user.module;

import com.dj.atm.core.service.HashingService;
import com.dj.atm.core.service.impl.HashingServiceImpl;
import com.dj.atm.user.dao.UserDao;
import com.dj.atm.user.dao.impl.UserDaoImpl;
import com.dj.atm.user.service.UserService;
import com.dj.atm.user.service.impl.UserServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class UserModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class).in(Scopes.SINGLETON);
        bind(UserDao.class).to(UserDaoImpl.class).in(Scopes.SINGLETON);
        bind(HashingService.class).to(HashingServiceImpl.class).in(Scopes.SINGLETON);
    }

}