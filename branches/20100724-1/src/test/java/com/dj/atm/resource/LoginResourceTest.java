package com.dj.atm.resource;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.User;
import com.dj.atm.core.util.WrappedResponse;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

/**
 * Created by IntelliJ IDEA.
 * User: ScriptRunner
 * Date: Apr 23, 2010
 * Time: 10:45:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginResourceTest extends JerseyTest {
    @Override
    protected AppDescriptor configure() {
        ClientConfig cc = new DefaultClientConfig();

        return new WebAppDescriptor.Builder(
            "com.dj.atm.resource;com.dj.atm.core.converter;org.codehaus.jackson.jaxrs").contextPath(
            "atm-web").clientConfig(cc).build();
    }

    @Test
    public void testCheckSession() {
        System.out.println("Test     : testCheckSession()");

        WebResource webResouce = resource();

        System.out.println("Resource : " + webResouce.toString());

        GenericType<WrappedResponse<User>> genericType = new GenericType<WrappedResponse<User>>() {}
        ;

        System.out.println("Generic Type : " + genericType.getType().toString());

        // get the initial representation
        WrappedResponse<User> result =
            webResouce.path("webresources/login/check").accept("application/json").get(genericType);

        System.out.println("Result : " + result.getData().getName());

        // check that there are two changes entries
        assertTrue(!result.isSuccess());
    }
}



