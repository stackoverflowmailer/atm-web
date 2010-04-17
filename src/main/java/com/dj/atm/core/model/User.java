package com.dj.atm.core.model;

/**
 * Represents a user in the system.
 */
public class User {

    private static final long serialVersionUID = 1L;
    private String username;
    private String name;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final User other = (User) obj;

        if ((username == null) ? (other.username != null)
                : (!username.equals(other.username))) {
            return false;
        }

        if ((name == null) ? (other.name != null) : (!name.equals(other.name))) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = (19 * hash) + ((username != null) ? username.hashCode() : 0);
        hash = (19 * hash) + ((name != null) ? name.hashCode() : 0);

        return hash;
    }

}
