package com.dj.atm.core.orm.session;

//~--- non-JDK imports --------------------------------------------------------

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.server.ConnectionPool;
import org.eclipse.persistence.sessions.server.ServerSession;

/**
 * Created by IntelliJ IDEA.
 * User: ScriptRunner
 * Date: Mar 21, 2010
 * Time: 12:15:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AtmSessionCustomizer implements SessionCustomizer {
    @Override
    public void customize(Session session) throws Exception {
        ServerSession  serverSession            = (ServerSession) session;
        int            work                     = serverSession.getNumberOfActiveUnitsOfWork();
        ConnectionPool cPool                    = serverSession.getConnectionPool("default");
        int            totalNumberOfConnections = cPool.getTotalNumberOfConnections();

        System.out.println("Details- NumberOfActiveUnitsOfWork : " + work + ", ConnectionPool size : "
                           + totalNumberOfConnections);

        // List<Accessor> list = cPool.getConnectionsAvailable();
        // for (Accessor a : list){
        // boolean b = a.isInTransaction();
        // int count = a.getCallCount();
        // boolean valid = a.isValid();
        // System.out.println("Accessor Details- In Txn : " + b + ", Call Count : " + count + ", Valid : " + valid);
        // }
    }
}



