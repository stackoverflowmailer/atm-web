package com.dj.atm.core.module;

import com.google.inject.AbstractModule;
import com.wideplay.warp.persist.jpa.JpaUnit;

public class PerisistenceModule extends AbstractModule {

    private static final String PERSISTENCE_UNIT = "atm";

    @Override
    protected void configure() {
        //PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION).forAll(Matchers.any()).buildModule();
        bindConstant().annotatedWith(JpaUnit.class).to(PERSISTENCE_UNIT);
    }
}
