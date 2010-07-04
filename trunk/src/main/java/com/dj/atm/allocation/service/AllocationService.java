package com.dj.atm.allocation.service;

import com.dj.atm.allocation.model.Allocation;
import com.dj.atm.allocation.model.Standard;
import com.dj.atm.core.model.QueryParameter;
import com.lowagie.text.pdf.AcroFields;

import java.util.Date;
import java.util.List;

/**
 * @author Script Runner
 */
public interface AllocationService {

    public Allocation getAllocation(Long aLong);

    public List<Allocation> getAllocations(QueryParameter qp);

    public Allocation save(Allocation allocation);

    public void remove(Allocation allocation);

    public Allocation getAllocationsForMonth(Date d);

    public Allocation getAllocationsForItemForMonth(AcroFields.Item i, Date d);

    public Allocation getAllocationsForItemForMonthForStandard(AcroFields.Item i, Date d, Standard s);

}
