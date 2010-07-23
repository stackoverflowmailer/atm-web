package com.dj.atm.allocation.dao.impl;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.allocation.dao.AllocationDao;
import com.dj.atm.allocation.model.Allocation;
import com.dj.atm.allocation.model.Standard;
import com.dj.atm.core.dao.impl.AbstractJpaDaoImpl;
import com.dj.atm.core.model.QueryParameter;

import com.lowagie.text.pdf.AcroFields;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ScriptRunner
 * Date: Jun 27, 2010
 * Time: 12:32:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllocationDaoImpl extends AbstractJpaDaoImpl<Long, Allocation> implements AllocationDao {
    @Override
    public Allocation getAllocationsForMonth(Date d) {
        return null;    // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Allocation getAllocationsForItemForMonth(AcroFields.Item i, Date d) {
        return null;    // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Allocation getAllocationsForItemForMonthForStandard(AcroFields.Item i, Date d, Standard s) {
        return null;    // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Allocation> getAllocations(QueryParameter qp) {
        return null;    // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Allocation save(Allocation allocation) {
        return null;    // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void remove(Allocation allocation) {

        // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Allocation getAllocation(Long aLong) {
        return null;    // To change body of implemented methods use File | Settings | File Templates.
    }
}



