/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dj.atm.developer.model;

/**
 * 
 * @author ScriptRunner
 */
public class User {

    private String username;

    /*
     * public User() { }
     */

    public User(String username) {
	this.username = username;
    }

    public String getUsername() {
	return this.username;
    }

    public void setUsername(String username) {
	this.username = username;
    }
}
