package com.dj.atm.developer.model;

import com.dj.atm.core.model.Entity;

import java.util.Date;

/**
 * Represents a developer entity in the system.
 */
public class Developer extends Entity {

    private static final long serialVersionUID = 1L;
    private String title;
    private Name name;
    private Phone landPhone;
    private Phone mobilePhone;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public Phone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Phone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

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
}
