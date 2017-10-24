package com.zautomate.modules.metric;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class MetricServiceImpl implements MetricService {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean updateMetrics() {
        return entityManager.createStoredProcedureQuery("UPDATE_METRICS").execute();
    }

    @Override
    public Map<String, Object> getAllMetrics(String manufacturer, String distributor, String var, String salesRep) {
        return null;
    }


}
