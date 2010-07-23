package com.dj.atm.core.service.impl;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.service.HashingService;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Script Runner
 * @since 0.0.1
 */
public class HashingServiceImpl implements HashingService {
    private static Log logger = LogFactory.getLog(HashingServiceImpl.class);

    /**
     * Get the hash of the input string using MD5 algorithm.
     *
     * @param input the string for which the hash will be returned.
     * @return the MD5 hash.
     */
    @Override
    public String getHash(String input) {
        return DigestUtils.md5Hex(input);
    }
}



