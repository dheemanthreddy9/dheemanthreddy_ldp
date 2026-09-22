package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


@Component
public class CustomBeanLifecycleLogger implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(CustomBeanLifecycleLogger.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.startsWith("centralHubService") || beanName.startsWith("deviceConnectionTask")) {
            logger.info("[LIFECYCLE STEP 3] BeanPostProcessor.postProcessBeforeInitialization() for bean: {}", beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.startsWith("centralHubService") || beanName.startsWith("deviceConnectionTask")) {
            logger.info("[LIFECYCLE STEP 5] BeanPostProcessor.postProcessAfterInitialization() for bean: {}", beanName);
        }
        return bean;
    }
}
