package com.dj.atm.core.service;

/**
 * @author Script Runner
 * @since 0.0.1
 */
public interface HashingService {

    /**
     * Get the hash of the input string using MD5 algorithm.
     *
     * @param input the string for which the hash will be returned.
     * @return the MD5 hash.
     */
    public String getHash(String input);
}


//~ Formatted by Jindent --- http://www.jindent.com
