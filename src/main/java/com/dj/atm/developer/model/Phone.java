package com.dj.atm.developer.model;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.converter.CustomIntegerDeserializer;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * @author Script Runner
 */
public class Phone {

    /**
     * Country Code
     */
    private int countryCode;
    private int number;
    private int stdCode;

    public Phone() {}

    public Phone(int countryCode, int stdCode, int number) {
        this.countryCode = countryCode;
        this.stdCode     = stdCode;
        this.number      = number;
    }

    public int getCountryCode() {
        return countryCode;
    }

    @JsonDeserialize(using = CustomIntegerDeserializer.class)
    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getStdCode() {
        return stdCode;
    }

    @JsonDeserialize(using = CustomIntegerDeserializer.class)
    public void setStdCode(int stdCode) {
        this.stdCode = stdCode;
    }

    public int getNumber() {
        return number;
    }

    @JsonDeserialize(using = CustomIntegerDeserializer.class)
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



