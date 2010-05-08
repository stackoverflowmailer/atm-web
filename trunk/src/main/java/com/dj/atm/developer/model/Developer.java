package com.dj.atm.developer.model;

import com.dj.atm.core.model.Entity;

import java.util.Date;

/**
 * Represents a developer entity in the system.
 */
public class Developer extends Entity {

    private static final long serialVersionUID = 1L;
    private Name name;
    private Phone landPhone;
    // private Phone mobilePhone;
    private Band band;
    /**
     * Date of joining the project *
     */
    private Date doj;
    /**
     * Date of leaving the project *
     */
    private Date dol;
    private String bloodGroup;

    public Name getName() {
	return name;
    }

    public void setName(Name name) {
	this.name = name;
    }

    public Phone getLandPhone() {
	return landPhone;
    }

    public void setLandPhone(Phone landPhone) {
	this.landPhone = landPhone;
    }

    /*
     * public Phone getMobilePhone() { return mobilePhone; }
     * 
     * public void setMobilePhone(Phone mobilePhone) { this.mobilePhone =
     * mobilePhone; }
     */

    public Band getBand() {
	return band;
    }

    public void setBand(Band band) {
	this.band = band;
    }

    public Date getDoj() {
	return doj;
    }

    public void setDoj(Date doj) {
	this.doj = doj;
    }

    public Date getDol() {
	return dol;
    }

    public void setDol(Date dol) {
	this.dol = dol;
    }

    public String getBloodGroup() {
	return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
	this.bloodGroup = bloodGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (band != developer.band) return false;
        if (bloodGroup != null ? !bloodGroup.equals(developer.bloodGroup) : developer.bloodGroup != null) return false;
        if (doj != null ? !doj.equals(developer.doj) : developer.doj != null) return false;
        if (dol != null ? !dol.equals(developer.dol) : developer.dol != null) return false;
        if (landPhone != null ? !landPhone.equals(developer.landPhone) : developer.landPhone != null) return false;
        if (name != null ? !name.equals(developer.name) : developer.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (landPhone != null ? landPhone.hashCode() : 0);
        result = 31 * result + (band != null ? band.hashCode() : 0);
        result = 31 * result + (doj != null ? doj.hashCode() : 0);
        result = 31 * result + (dol != null ? dol.hashCode() : 0);
        result = 31 * result + (bloodGroup != null ? bloodGroup.hashCode() : 0);
        return result;
    }
}
