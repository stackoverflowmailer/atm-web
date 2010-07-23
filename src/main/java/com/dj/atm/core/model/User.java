package com.dj.atm.core.model;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.developer.model.Name;

//~--- JDK imports ------------------------------------------------------------

import java.util.Date;

/**
 * Represents a user in the system.
 */
public class User extends Entity {
    private static final long serialVersionUID = 1L;

    // private String passwordHash;
    private Date   creationDate;
    private Name   name;
    private String password;
    private String username;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//  public String getPasswordHash() {
//      return passwordHash;
//  }
//
//  public void setPasswordHash(String passwordHash) {
//      this.passwordHash = passwordHash;
//  }
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        User user = (User) o;

        if (!name.equals(user.name)) {
            return false;
        }

        if (!username.equals(user.username)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();

        result = 31 * result + name.hashCode();

        return result;
    }
}



