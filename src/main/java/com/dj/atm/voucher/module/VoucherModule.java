package com.dj.atm.voucher.module;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.voucher.dao.VoucherDao;
import com.dj.atm.voucher.dao.impl.VoucherDaoImpl;
import com.dj.atm.voucher.service.VoucherService;
import com.dj.atm.voucher.service.impl.VoucherServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * @author Script Runner
 */
public class VoucherModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(VoucherService.class).to(VoucherServiceImpl.class).in(Scopes.SINGLETON);
        bind(VoucherDao.class).to(VoucherDaoImpl.class).in(Scopes.SINGLETON);
    }
}



