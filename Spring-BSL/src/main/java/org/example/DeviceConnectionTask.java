package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeviceConnectionTask {

    private static final Logger logger = LoggerFactory.getLogger(DeviceConnectionTask.class);
    private final String taskId;

    public DeviceConnectionTask() {
        this.taskId = UUID.randomUUID().toString().substring(0, 8);
        logger.info("[LIFECYCLE STEP 1] DeviceConnectionTask Constructor called. Task ID: {} (HashCode: {})", 
                taskId, System.identityHashCode(this));
    }

    @PostConstruct
    public void initTask() {
        logger.info("[LIFECYCLE STEP 4] @PostConstruct method executed for DeviceConnectionTask ({})", taskId);
    }

    public void executeConnection(String deviceName) {
        logger.info("[BEAN USAGE] Connecting device '{}' using Task ID: {}", deviceName, taskId);
    }

    @PreDestroy
    public void cleanupTask() {
        // NOTE: Spring IoC container does NOT automatically call @PreDestroy on Prototype beans!
        logger.info("[LIFECYCLE DESTROY] DeviceConnectionTask cleanup called for Task ID: {}", taskId);
    }

    public String getTaskId() {
        return taskId;
    }
}
