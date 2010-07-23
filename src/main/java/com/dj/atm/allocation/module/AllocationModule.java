package com.dj.atm.allocation.module;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.allocation.dao.AllocationDao;
import com.dj.atm.allocation.dao.impl.AllocationDaoImpl;
import com.dj.atm.allocation.service.AllocationService;
import com.dj.atm.allocation.service.impl.AllocationServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * @author Script Runner
 */
public class AllocationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AllocationService.class).to(AllocationServiceImpl.class).in(Scopes.SINGLETON);
        bind(AllocationDao.class).to(AllocationDaoImpl.class).in(Scopes.SINGLETON);
    }
}



