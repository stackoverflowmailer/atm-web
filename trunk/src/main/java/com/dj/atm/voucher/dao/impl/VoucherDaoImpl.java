package com.dj.atm.voucher.dao.impl;

import com.dj.atm.core.dao.impl.AbstractJpaDaoImpl;
import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.voucher.dao.VoucherDao;
import com.dj.atm.voucher.model.Voucher;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * @author Script Runner
 * @since 0.0.1
 */
public class VoucherDaoImpl extends AbstractJpaDaoImpl<Long, Voucher>
        implements VoucherDao {

    /**
     * Get all the vouchers in the team regardless
     * of any condition.
     * <p/>
     * The results are paginated to improve
     * load / performance at the server and the client.
     *
     * @return list of all vouchers.
     */
    public List<Voucher> getVouchers(QueryParameter qp) {
        final Query query = entityManager.get().createNamedQuery("getAllVouchers");
        query.setFirstResult(qp.getOffset());
        query.setMaxResults(qp.getLimit());
        List<Voucher> vouchers = query.getResultList();
        return vouchers;
    }

    /**
     * Get all the vouchers matching the supplied name parameter.
     *
     * @param date - the search key.
     * @return the list of all vouchers with the matching names.
     */
    @Override
    public List<Voucher> getVoucherByDate(Date date) {
        final Query query = entityManager.get().createNamedQuery("getVoucherByDate");
        @SuppressWarnings("unchecked")
        List<Voucher> voucherList = query.getResultList();
        return voucherList;
    }
}


