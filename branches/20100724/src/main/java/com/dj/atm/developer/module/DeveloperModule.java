package com.dj.atm.developer.module;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.developer.dao.DeveloperDao;
import com.dj.atm.developer.dao.impl.DeveloperDaoImpl;
import com.dj.atm.developer.service.DeveloperService;
import com.dj.atm.developer.service.impl.DeveloperServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class DeveloperModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DeveloperService.class).to(DeveloperServiceImpl.class).in(Scopes.SINGLETON);
        bind(DeveloperDao.class).to(DeveloperDaoImpl.class).in(Scopes.SINGLETON);
        bindConstant().annotatedWith(Names.named("message")).to("Hello World");

        // bindConstant().annotatedWith(Names.named("message")).to("Hello, World!");
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
