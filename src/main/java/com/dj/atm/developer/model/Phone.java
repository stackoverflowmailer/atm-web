package com.dj.atm.developer.model;

/**
 * Created by IntelliJ IDEA. User: ScriptRunner Date: Feb 25, 2010 Time:
 * 11:56:45 PM To change this template use File | Settings | File Templates.
 */
public class Phone {
    /**
     * Country Code*
     */
    private int countryCode;
    private int stdCode;
    private int number;

    public Phone() {

    }

    public Phone(int countryCode, int stdCode, int number) {
	this.countryCode = countryCode;
	this.stdCode = stdCode;
	this.number = number;
    }

    public int getCountryCode() {
	return countryCode;
    }

    public void setCountryCode(int countryCode) {
	this.countryCode = countryCode;
    }

    public int getStdCode() {
	return stdCode;
    }

    public void setStdCode(int stdCode) {
	this.stdCode = stdCode;
    }

    public int getNumber() {
	return number;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("Phone");
	sb.append("{countryCode=").append(countryCode);
	sb.append(", stdCode=").append(stdCode);
	sb.append(", number=").append(number);
	sb.append('}');
	return sb.toString();
    }
}
