package com.dj.atm.voucher.dao;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.dao.Dao;
import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.voucher.model.Voucher;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;
import java.util.List;

/**
 * @author Script Runner
 * @since 0.0.1
 */
public interface VoucherDao extends Dao<Long, Voucher> {

    /**
     * Get all the developers in the team regardless
     * of any condition.
     * <p/>
     * Implementation should preferably paginate results to improve
     * load / performance at the server and the client.
     *
     * @param qp parameter object for this query encapsulating limit and offset.
     * @return list of all developers.
     */
    public List<Voucher> getVouchers(QueryParameter qp);

    public List<Voucher> getVoucherByDate(Date date);
}


//~ Formatted by Jindent --- http://www.jindent.com
