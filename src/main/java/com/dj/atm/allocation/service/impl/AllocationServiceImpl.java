package com.dj.atm.allocation.service.impl;

import com.dj.atm.allocation.dao.AllocationDao;
import com.dj.atm.allocation.model.Allocation;
import com.dj.atm.allocation.model.Standard;
import com.dj.atm.allocation.service.AllocationService;
import com.dj.atm.core.model.QueryParameter;
import com.lowagie.text.pdf.AcroFields;
import com.wideplay.warp.persist.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Script Runner
 */
public class AllocationServiceImpl implements AllocationService {

    private AllocationDao allocationDao;

    public AllocationDao getAllocationDao() {
        return allocationDao;
    }

    public void setAllocationDao(AllocationDao allocationDao) {
        this.allocationDao = allocationDao;
    }

    @Override
    @Transactional
    public Allocation getAllocationsForMonth(Date d) {
        return getAllocationDao().getAllocationsForMonth(d);
    }

    @Override
    @Transactional
    public Allocation getAllocationsForItemForMonth(AcroFields.Item i, Date d) {
        return getAllocationDao().getAllocationsForItemForMonth(i,d);
    }

    @Override
    @Transactional
    public Allocation getAllocationsForItemForMonthForStandard(AcroFields.Item i, Date d, Standard s) {
        return getAllocationDao().getAllocationsForItemForMonthForStandard(i,d,s);
    }


    @Override
    @Transactional
    public Allocation getAllocation(Long aLong) {
        return getAllocationDao().getAllocation(aLong);
    }

    @Override
    @Transactional
    public List<Allocation> getAllocations(QueryParameter qp) {
        return getAllocationDao().getAllocations(qp);
    }

    @Override
    public Allocation save(Allocation allocation) {
        return getAllocationDao().save(allocation);
    }

    @Override
    public void remove(Allocation allocation) {
        getAllocationDao().remove(allocation);
    }
}
