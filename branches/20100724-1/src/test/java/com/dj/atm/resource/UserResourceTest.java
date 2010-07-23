package com.dj.atm.resource;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.User;
import com.dj.atm.core.util.WrappedResponse;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

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

import javax.ws.rs.core.MediaType;

/**
 * @author Script Runner
 */
public class UserResourceTest extends JerseyTest {
    @Override
    protected AppDescriptor configure() {
        ClientConfig cc = new DefaultClientConfig();

        cc.getClasses().add(JacksonJsonProvider.class);

        return new WebAppDescriptor.Builder(
            "com.dj.atm.resource;com.dj.atm.core.converter;org.codehaus.jackson.jaxrs").contextPath(
            "atm-web").clientConfig(cc).build();
    }

    public void testSave() throws Exception {}

    @Test
    public void testGetUsers() throws Exception {

/*
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJsonProvider.class);
        Client clientWithJacksonSerializer = Client.create(cc);*/
        WebResource webResource = resource();

        System.out.println("Resource : " + webResource.toString());

        GenericType<WrappedResponse<User>> genericType = new GenericType<WrappedResponse<User>>() {}
        ;

        System.out.println("Generic Type : " + genericType.getType().toString());

        // get the initial representation
        WrappedResponse<User> result =
            webResource.path("webresources/user/users").accept(MediaType.APPLICATION_JSON).get(genericType);

        System.out.println("Result : " + result.getData().getName());

        // check that there are two changes entries
        assertTrue(!result.isSuccess());
    }
}



