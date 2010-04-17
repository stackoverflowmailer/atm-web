package com.dj.atm.core.listener;

import com.dj.atm.core.module.PerisistenceModule;
import com.dj.atm.core.security.AtmSecurityFilter;
import com.dj.atm.developer.module.DeveloperModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;
import org.guiceyfruit.jsr250.Jsr250Module;

import java.util.HashMap;
import java.util.Map;

public class AtmContextListener extends GuiceServletContextListener {

    /**
     * Jersey scanner will be looking in these packages for resources and providers
     */
    private static final String SCANNING_LOCATIONS = "com.dj.atm.resource;com.dj.atm.core.converter;org.codehaus.jackson.jaxrs";

    @Override
    protected Injector getInjector() {
        final Map<String, String> servletParameters = new HashMap<String, String>();
        servletParameters.put(PackagesResourceConfig.PROPERTY_PACKAGES, SCANNING_LOCATIONS);
        servletParameters.put("com.sun.jersey.config.feature.Trace", "true");

        Injector injector = Guice.createInjector(
                new DeveloperModule(),
                new PerisistenceModule(),
                PersistenceService
                        .usingJpa()
                        .across(UnitOfWork.TRANSACTION)
                        .buildModule(),
                new Jsr250Module(),
                new ServletModule() {

                    @Override
                    protected void configureServlets() {
                        filter("/webresources/*").through(AtmSecurityFilter.class);
                        serve("/webresources/*").with(GuiceContainer.class, servletParameters);
                    }
                });
        return injector;
    }

}
