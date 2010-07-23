package com.dj.atm.resource;

//~--- non-JDK imports --------------------------------------------------------

import com.dj.atm.core.model.QueryParameter;
import com.dj.atm.core.util.WrappedResponse;
import com.dj.atm.voucher.model.Voucher;
import com.dj.atm.voucher.service.VoucherService;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//~--- JDK imports ------------------------------------------------------------

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author Script Runner
 */
@Path("/voucher")
public class VoucherResource {

    /**
     * Logger Configuration 
     */
    private static Log logger = LogFactory.getLog(VoucherResource.class);

    /**
     * Service Instance 
     */
    private final VoucherService voucherService;

    @Inject
    public VoucherResource(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, "text/json" })
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getVoucher")
    public WrappedResponse<Voucher> getVoucher(@FormParam("id") String id) {
        Voucher                  voucher  = voucherService.getVoucher(Long.valueOf(id));
        WrappedResponse<Voucher> response = new WrappedResponse<Voucher>(true, voucher);

        return response;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, "text/json" })
    @Path("/vouchers")
    public WrappedResponse<Collection> getVouchers(@Context HttpServletRequest request) {
        QueryParameter      qp       = HttpServletUtil.getQueryParameter(request);
        List<Voucher>       vouchers = voucherService.getVouchers(qp);
        Collection<Voucher> filtered = Collections2.filter(vouchers, new Predicate<Voucher>() {
            @Override
            public boolean apply(Voucher voucher) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Voucher  : " + voucher.getId() + " : " + voucher.getName());
                }

                if (voucher.getName() == null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Voucher name found to null for : " + voucher.getId());
                    }

                    return false;
                }

                return true;
            }
        });
        WrappedResponse<Collection> response = new WrappedResponse<Collection>(true, filtered);

        return response;
    }

    @Path("/saveVoucher")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Map saveVoucher(Voucher voucher) {
        Voucher persisted = voucherService.save(voucher);
        Map     rValue    = new HashMap();

        rValue.put("success", true);
        rValue.put("data", persisted);

        return rValue;
    }

    @Path("/deleteVoucher")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public Map deleteVoucher(Voucher voucher) {
        voucherService.remove(voucher);

        Map rValue = new HashMap();

        rValue.put("success", true);
        rValue.put("data", voucher);

        return rValue;
    }
}



