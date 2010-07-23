package com.dj.atm.developer.model;

/**
 * @author Script Runner
 */
public class Name {
    private String firstName;
    private String lastName;
    private String middleName;

    public Name() {}

    public Name(String firstName, String middleName, String lastName) {
        this.firstName  = firstName;
        this.middleName = middleName;
        this.lastName   = lastName;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Name name = (Name) o;

        if (!firstName.equals(name.firstName)) {
            return false;
        }

        if (!lastName.equals(name.lastName)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();

        result = 31 * result + lastName.hashCode();

        return result;
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



