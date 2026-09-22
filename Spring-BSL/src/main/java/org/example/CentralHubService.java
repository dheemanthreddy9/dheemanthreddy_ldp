package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CentralHubService implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(CentralHubService.class);
    private final String hubId;

    public CentralHubService() {
        this.hubId = UUID.randomUUID().toString().substring(0, 8);
        logger.info("[LIFECYCLE STEP 1] CentralHubService Constructor called. Hub ID: {} (HashCode: {})", 
                hubId, System.identityHashCode(this));
    }

    @PostConstruct
    public void customInit() {
        logger.info("[LIFECYCLE STEP 4a] @PostConstruct method executed for CentralHubService.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("[LIFECYCLE STEP 4b] InitializingBean.afterPropertiesSet() executed for CentralHubService.");
    }

    public void runDiagnostics() {
        logger.info("[BEAN USAGE] CentralHubService ({}) performing system health check.", hubId);
    }

    @PreDestroy
    public void customDestroy() {
        logger.info("[LIFECYCLE STEP 6a] @PreDestroy method executed for CentralHubService.");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("[LIFECYCLE STEP 6b] DisposableBean.destroy() executed for CentralHubService.");
    }

    public String getHubId() {
        return hubId;
    }
}
