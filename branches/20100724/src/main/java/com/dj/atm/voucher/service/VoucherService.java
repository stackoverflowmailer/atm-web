package com.dj.atm.voucher.service;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.voucher.model.Voucher;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;
import java.util.List;

/**
 * @author Script Runner
 */
public interface VoucherService {

    /**
     * Return the voucher matching the provided id;
     *
     * @param id - used as the search key.
     * @return the voucher matching the search key, if no voucher is found
     *         with the matching key, then return null
     */
    public Voucher getVoucher(Long id);

    /**
     * Get all the vouchers in the team regardless
     * of any condition.
     * <p/>
     * Implementation should preferably paginate results to improve
     * load / performance at the server and the client.
     *
     * @param qp parameter object for this query encapsulating limit and offset.
     * @return list of all vouchers.
     */
    public List<Voucher> getVouchers(QueryParameter qp);

    /**
     * Get all vouchers matching the supplied <code>Date</code> criteria. Note
     * that if attributes of the Name contain patterns then a substring matching
     * should be done.
     *
     * @param date the pattern to be searched for.
     * @return the list of vouchers having matching names.
     */
    public List<Voucher> getVoucherByDate(Date date);

    /**
     * Persist the voucher and return the id.
     *
     * @param voucher the voucher entity to be saved.
     * @return the voucher instance that just got persisted.
     */
    public Voucher save(Voucher voucher);

    /**
     * Removes the voucher from the team
     *
     * @param voucher the voucher who should be removed from the team.
     */
    public void remove(Voucher voucher);
}


//~ Formatted by Jindent --- http://www.jindent.com
