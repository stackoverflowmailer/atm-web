package com.dj.atm.allocation.model;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.Entity;

/**
 * @author Script Runner
 *
 */
public class Allocation extends Entity {
    private double allocated;
    private double free;
    private String item;
    private String standard;
    private String students;
    private double used;

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getAllocated() {
        return allocated;
    }

    public void setAllocated(double allocated) {
        this.allocated = allocated;
    }

    public double getUsed() {
        return used;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public double getFree() {
        return free;
    }

    public void setFree(double free) {
        this.free = free;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Allocation");
        sb.append("{standard='").append(standard).append('\'');
        sb.append(", students='").append(students).append('\'');
        sb.append(", item='").append(item).append('\'');
        sb.append(", allocated=").append(allocated);
        sb.append(", used=").append(used);
        sb.append(", free=").append(free);
        sb.append('}');

        return sb.toString();
    }
}



