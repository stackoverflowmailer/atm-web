package com.dj.atm.voucher.service.impl;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.voucher.dao.VoucherDao;
import com.dj.atm.voucher.model.Voucher;
import com.dj.atm.voucher.service.VoucherService;

import com.wideplay.warp.persist.Transactional;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;
import java.util.List;

/**
 * @author Script Runner
 */
@Transactional
public class VoucherServiceImpl implements VoucherService {
    private VoucherDao voucherDao;

    public VoucherDao getVoucherDao() {
        return voucherDao;
    }

    public void setVoucherDao(VoucherDao voucherDao) {
        this.voucherDao = voucherDao;
    }

    @Override
    public Voucher getVoucher(Long id) {
        return getVoucherDao().findById(id);
    }

    @Override
    public List<Voucher> getVouchers(QueryParameter qp) {
        return getVoucherDao().getVouchers(qp);
    }

    @Override
    public List<Voucher> getVoucherByDate(Date date) {
        return getVoucherDao().getVoucherByDate(date);
    }

    @Override
    public Voucher save(Voucher voucher) {
        return getVoucherDao().save(voucher);
    }

    @Override
    public void remove(Voucher voucher) {
        getVoucherDao().delete(voucher);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
