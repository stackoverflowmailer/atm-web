package com.dj.atm.voucher.model;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.Entity;
import com.dj.atm.developer.model.Name;

//~--- JDK imports ------------------------------------------------------------

import java.math.BigDecimal;

import java.util.Date;

/**
 * @author Script Runner
 */
public class Voucher extends Entity {
    private static final long serialVersionUID = 1L;
    private String            job;

    /* private Date issueDate; */
    private Name       name;
    private String     remarks;
    private BigDecimal voucherAmt;
    private Date       voucherDate;
    private Long       voucherNo;

    public Long getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(Long voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Date getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }

    /*
     *  public Date getIssueDate() {
     *    return issueDate;
     * }
     *
     * public void setIssueDate(Date issueDate) {
     *    this.issueDate = issueDate;
     * }
     */
    public BigDecimal getVoucherAmt() {
        return voucherAmt;
    }

    public void setVoucherAmt(BigDecimal voucherAmt) {
        this.voucherAmt = voucherAmt;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Voucher");
        sb.append("{voucherNo=").append(voucherNo);
        sb.append(", voucherDate=").append(voucherDate);

        /* sb.append(", issueDate=").append(issueDate); */
        sb.append(", name=").append(name);
        sb.append(", voucherAmt=").append(voucherAmt);
        sb.append(", job='").append(job).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append('}');

        return sb.toString();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
