package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("            SPRING BEAN SCOPES & LIFECYCLE DEMONSTRATION");


        logger.info("[1] INITIALIZING APPLICATION CONTEXT...");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        logger.info("[2] DEMONSTRATING SINGLETON BEAN SCOPE (Default)");
        CentralHubService hub1 = context.getBean(CentralHubService.class);
        CentralHubService hub2 = context.getBean(CentralHubService.class);

        hub1.runDiagnostics();
        
        logger.info("Instance 1 HashCode : {}", System.identityHashCode(hub1));
        logger.info("Instance 2 HashCode : {}", System.identityHashCode(hub2));
        logger.info("Are both instances IDENTICAL? -> {}", (hub1 == hub2));

        logger.info("[3] DEMONSTRATING PROTOTYPE BEAN SCOPE");

        DeviceConnectionTask task1 = context.getBean(DeviceConnectionTask.class);
        task1.executeConnection("Smart Thermostat");


        DeviceConnectionTask task2 = context.getBean(DeviceConnectionTask.class);
        task2.executeConnection("Living Room Light");

        logger.info("Task 1 HashCode : {} | ID: {}", System.identityHashCode(task1), task1.getTaskId());
        logger.info("Task 2 HashCode : {} | ID: {}", System.identityHashCode(task2), task2.getTaskId());
        logger.info("Are both instances IDENTICAL? -> {}", (task1 == task2));


        logger.info("[4] CLOSING APPLICATION CONTEXT (Destruction Phase)");

        context.close();

    }
}
