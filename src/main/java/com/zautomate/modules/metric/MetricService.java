package com.zautomate.modules.metric;

import java.util.Map;

public interface MetricService {

    boolean updateMetrics();
    Map<String, Object> getAllMetrics(String manufacturer, String distributor, String var, String salesRep);

}
