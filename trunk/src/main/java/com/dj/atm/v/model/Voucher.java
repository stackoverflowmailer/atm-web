package com.dj.atm.voucher.model;

import com.dj.atm.core.model.Entity;
import com.dj.atm.developer.model.Name;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Script Runner
 */
public class Voucher extends Entity {

    private static final long serialVersionUID = 1L;
    private Long voucherNo;
    private Date voucherDate;
    private Date issueDate;
    private Name issueTo;
    private BigDecimal voucherAmt;
    private String job;
    private String remarks;

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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Name getIssueTo() {
        return issueTo;
    }

    public void setIssueTo(Name issueTo) {
        this.issueTo = issueTo;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Voucher");
        sb.append("{voucherNo=").append(voucherNo);
        sb.append(", voucherDate=").append(voucherDate);
        sb.append(", issueDate=").append(issueDate);
        sb.append(", issueTo=").append(issueTo);
        sb.append(", voucherAmt=").append(voucherAmt);
        sb.append(", job='").append(job).append('\'');
        sb.append(", remarks='").append(remarks).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
