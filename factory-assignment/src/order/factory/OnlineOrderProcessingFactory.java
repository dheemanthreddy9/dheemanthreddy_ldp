package order.factory;

import order.processor.OrderProcessor;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * OnlineOrderProcessingFactory implements the Factory Design Pattern strictly following
 * the Open-Closed Principle (OCP: Open for Extension, Closed for Modification).
 *
 * HOW OCP IS PRESERVED:
 * 1. Traditional Factory anti-pattern uses hardcoded switch/if-else statements:
 *       if (category.equals("FOOD")) return new FoodOrderProcessor();
 *    Adding a new category forces modification of this factory class, violating OCP.
 *
 * 2. This factory uses Dynamic Discovery via:
 *    - Java Service Provider Interface (java.util.ServiceLoader)
 *    - Optional classpath properties configuration (order-processors.properties)
 *    - Runtime dynamic registration (registerProcessor)
 *
 * Consequently:
 * To add a brand-new order category (such as Electronics, Grocery, Books, Furniture):
 * - Create a new class implementing OrderProcessor (e.g., ElectronicsOrderProcessor).
 * - Declare it in META-INF/services/order.processor.OrderProcessor (or properties).
 * - THIS FACTORY CLASS DOES NOT REQUIRE A SINGLE LINE OF CODE CHANGE!
 */
public class OnlineOrderProcessingFactory {

    private static final Map<String, OrderProcessor> REGISTRY = new ConcurrentHashMap<>();
    private static volatile boolean initialized = false;

    // Private constructor to prevent direct instantiation (Factory utility class)
    private OnlineOrderProcessingFactory() {}

    /**
     * Initializes the factory by dynamically discovering all OrderProcessor implementations
     * available on the classpath without any hardcoded class references.
     */
    private static synchronized void initializeIfNecessary() {
        if (!initialized) {
            reloadProcessors();
            initialized = true;
        }
    }

    /**
     * Discovers and registers processors using:
     * 1. Java ServiceLoader SPI (standard plugin mechanism)
     * 2. Classpath configuration (order-processors.properties)
     */
    public static synchronized void reloadProcessors() {
        // 1. ServiceLoader discovery (META-INF/services/order.processor.OrderProcessor)
        ServiceLoader<OrderProcessor> loader = ServiceLoader.load(OrderProcessor.class);
        for (OrderProcessor processor : loader) {
            registerProcessor(processor);
        }

        // 2. Classpath properties fallback/supplement (order-processors.properties)
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
            // Optional properties file, ServiceLoader is primary
        }
    }

    /**
     * Allows dynamic runtime registration of new processors (open for extension).
     */
    public static void registerProcessor(OrderProcessor processor) {
        if (processor != null && processor.getCategory() != null) {
            String key = processor.getCategory().trim().toUpperCase();
            REGISTRY.put(key, processor);
        }
    }

    /**
     * Factory Method to obtain the appropriate OrderProcessor for a given category.
     * Closed for modification: does not change when new categories are added.
     */
    public static OrderProcessor getProcessor(String category) {
        initializeIfNecessary();

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Order category cannot be null or empty.");
        }

        String key = category.trim().toUpperCase();
        OrderProcessor processor = REGISTRY.get(key);

        if (processor == null) {
            // Attempt to reload in case new providers were registered dynamically
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

    /**
     * Returns a set of all currently registered category names.
     */
    public static Set<String> getSupportedCategories() {
        initializeIfNecessary();
        return Collections.unmodifiableSet(new TreeSet<>(REGISTRY.keySet()));
    }
}
