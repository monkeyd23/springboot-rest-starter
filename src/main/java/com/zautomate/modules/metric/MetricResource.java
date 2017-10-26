package com.zautomate.modules.metric;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/metrics")
public class MetricResource {

    private MetricService metricService;

    @Inject
    public void setMetricService(MetricService metricService) {
        this.metricService = metricService;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Map<String, Object> getAllMetrics(
            @FormParam("manufacturer") String manufacturer,
            @FormParam("distributor") String distributor,
            @FormParam("var") String var,
            @FormParam("sales_rep") String salesRep) {

        return  metricService.getAllMetrics(manufacturer, distributor, var, salesRep);
    }
}
