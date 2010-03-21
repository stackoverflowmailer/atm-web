package com.dj.atm.developer.exception;

/**
 * This exception is thrown when a developer(s) with a specific criteria is not
 * found in the system.
 * 
 * Use this class rather than returning <code>null</code> in above scenarios.
 */
public class DeveloperNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public DeveloperNotFoundException(String message) {
	super(message);
    }
}
