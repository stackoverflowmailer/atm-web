package com.dj.atm.core.listener;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.allocation.module.AllocationModule;
import com.dj.atm.core.module.PersistenceModule;
import com.dj.atm.core.security.AtmSecurityFilter;
import com.dj.atm.developer.module.DeveloperModule;
import com.dj.atm.user.module.UserModule;
import com.dj.atm.voucher.module.VoucherModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import com.wideplay.warp.persist.PersistenceService;
import com.wideplay.warp.persist.UnitOfWork;

//~--- JDK imports ------------------------------------------------------------

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for the following tasks
 * <ol>
 * <li> Provides the list scanning locations/ packages for resources and providers for Jersey Servlet</li>
 * <li> Provides Guice Integration of dependence injection, transaction support and servlet modules</li>
 * <li> Routes any REST / AJAX request through security filter for checking authenticity of the calls</li>
 * <li> Forwards any REST / AJAX calls to its handler</li>
 * </ol>
 *
 * @author Script Runner
 * @since 0.0.1
 */
public class AtmContextListener extends GuiceServletContextListener {

    /**
     * Jersey scanner will be looking in these packages for resources and providers
     */
    private static final String SCANNING_LOCATIONS =
        "com.dj.atm.resource;com.dj.atm.core.converter;org.codehaus.jackson.jaxrs";

    @Override
    protected Injector getInjector() {
        final Map<String, String> servletParameters = new HashMap<String, String>();

        servletParameters.put(PackagesResourceConfig.PROPERTY_PACKAGES, SCANNING_LOCATIONS);
        servletParameters.put("com.sun.jersey.config.feature.Trace", "true");

        Injector injector = Guice.createInjector(new DeveloperModule(), new AllocationModule(), new VoucherModule(),
                                new UserModule(), new PersistenceModule(),
                                PersistenceService.usingJpa().across(UnitOfWork.TRANSACTION).buildModule(),

        // new Jsr250Module(),
        new ServletModule() {
            @Override
            protected void configureServlets() {

                // forward all Ajax Requests through AtmSecurityFilter implementation
                filter("/webresources/*").through(AtmSecurityFilter.class);
                serve("/webresources/*").with(GuiceContainer.class, servletParameters);
            }
        });

        return injector;
    }
}



