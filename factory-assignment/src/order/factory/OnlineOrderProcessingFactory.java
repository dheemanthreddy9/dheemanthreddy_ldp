package order.factory;

import order.processor.OrderProcessor;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineOrderProcessingFactory {

    private static final Map<String, OrderProcessor> REGISTRY = new ConcurrentHashMap<>();
    private static volatile boolean initialized = false;

    private OnlineOrderProcessingFactory() {}

    private static synchronized void initializeIfNecessary() {
        if (!initialized) {
            reloadProcessors();
            initialized = true;
        }
    }

    public static synchronized void reloadProcessors() {

        ServiceLoader<OrderProcessor> loader = ServiceLoader.load(OrderProcessor.class);
        for (OrderProcessor processor : loader) {
            registerProcessor(processor);
        }

        try (InputStream in = OnlineOrderProcessingFactory.class.getClassLoader()
                .getResourceAsStream("order-processors.properties")) {
            if (in != null) {
                Properties props = new Properties();
                props.load(in);
                for (String category : props.stringPropertyNames()) {
                    String className = props.getProperty(category).trim();
                    if (!className.isEmpty()) {
                        try {
                            Class<?> clazz = Class.forName(className);
                            if (OrderProcessor.class.isAssignableFrom(clazz)) {
                                OrderProcessor processor = (OrderProcessor) clazz.getDeclaredConstructor().newInstance();
                                registerProcessor(processor);
                            }
                        } catch (Exception ex) {
                            System.err.println("[Factory Warning] Could not load processor class: " + className + " (" + ex.getMessage() + ")");
                        }
                    }
                }
            }
        } catch (Exception ignored) {

        }
    }

    public static void registerProcessor(OrderProcessor processor) {
        if (processor != null && processor.getCategory() != null) {
            String key = processor.getCategory().trim().toUpperCase();
            REGISTRY.put(key, processor);
        }
    }

    public static OrderProcessor getProcessor(String category) {
        initializeIfNecessary();

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Order category cannot be null or empty.");
        }

        String key = category.trim().toUpperCase();
        OrderProcessor processor = REGISTRY.get(key);

        if (processor == null) {

            reloadProcessors();
            processor = REGISTRY.get(key);
        }

        if (processor == null) {
            throw new UnsupportedOperationException(String.format(
                    "No OrderProcessor registered for category '%s'. Currently supported categories: %s",
                    category, getSupportedCategories()));
        }

        return processor;
    }

    public static Set<String> getSupportedCategories() {
        initializeIfNecessary();
        return Collections.unmodifiableSet(new TreeSet<>(REGISTRY.keySet()));
    }
}
