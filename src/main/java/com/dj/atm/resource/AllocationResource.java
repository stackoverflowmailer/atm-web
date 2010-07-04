package com.dj.atm.resource;

import com.dj.atm.allocation.model.Allocation;
import com.dj.atm.allocation.service.AllocationService;
import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.util.WrappedResponse;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Script Runner
 */
@Path("/allocation")
public class AllocationResource {

    /**
     * Logger Configuration *
     */
    private static Log logger = LogFactory.getLog(AllocationResource.class);

    /**
     * Service Instance *
     */
    private final AllocationService allocationService;

    @Inject
    public AllocationResource(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, "text/json"})
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getAllocation")
    public WrappedResponse<Allocation> getAllocation(@FormParam("id") String id) {
        Allocation allocation = allocationService.getAllocation(Long.valueOf(id));
        WrappedResponse<Allocation> response = new WrappedResponse<Allocation>(true, allocation);
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, "text/json"})
    @Path("/allocations")
    public WrappedResponse<Collection> getAllocations(@Context HttpServletRequest request) {
        QueryParameter qp = HttpServletUtil.getQueryParameter(request);
        List<Allocation> allocations = allocationService.getAllocations(qp);
        Collection<Allocation> filtered = Collections2.filter(allocations, new Predicate<Allocation>() {
            @Override
            public boolean apply(Allocation allocation) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Allocation  : " + allocation.getId() + " : " + allocation.getItem());
                }
                if (allocation.getItem() == null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Allocation name found to null for : " + allocation.getId());
                    }
                    return false;
                }
                return true;
            }
        });
        WrappedResponse<Collection> response = new WrappedResponse<Collection>(true, filtered);
        return response;
    }

    @Path("/saveAllocation")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Map saveAllocation(
            Allocation allocation) {
        Allocation persisted = allocationService.save(allocation);
        Map rValue = new HashMap();
        rValue.put("success", true);
        rValue.put("data", persisted);
        return rValue;
    }


    @Path("/deleteAllocation")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Map deleteAllocation(
            Allocation allocation) {
        allocationService.remove(allocation);
        Map rValue = new HashMap();
        rValue.put("success", true);
        rValue.put("data", allocation);
        return rValue;
    }

}
