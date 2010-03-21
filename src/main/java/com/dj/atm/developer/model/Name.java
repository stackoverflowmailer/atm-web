package com.dj.atm.developer.model;

/**
 * Created by IntelliJ IDEA. User: ScriptRunner Date: Feb 25, 2010 Time:
 * 11:55:14 PM To change this template use File | Settings | File Templates.
 */
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    public Name() {

    }

    public Name(String firstName, String middleName, String lastName) {
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getMiddleName() {
	return middleName;
    }

    public void setMiddleName(String middleName) {
	this.middleName = middleName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	sb.append("Name");
	sb.append("{firstName='").append(firstName).append('\'');
	sb.append(", middleName='").append(middleName).append('\'');
	sb.append(", lastName='").append(lastName).append('\'');
	sb.append('}');
	return sb.toString();
    }
}
