package com.dj.atm.core.model;

/**
 * Created by IntelliJ IDEA.
 * User: ScriptRunner
 * Date: Apr 24, 2010
 * Time: 10:52:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryParameter {

    private int limit = 100;
    private int offset = 0;

    public QueryParameter() {
    }

    public QueryParameter(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

}

