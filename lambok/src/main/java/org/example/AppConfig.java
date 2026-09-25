package org.example;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan(basePackages = "org.example")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppConfig {

    String appName = "Spring + Lombok Unified App";

    @Getter(lazy = true)
    String heavyResource = loadHeavyResource();

    @Bean
    public AppInfo appInfo() {
        return new AppInfo(appName, "1.0.0");
    }

    private String loadHeavyResource() {
        log.info("--> Loading Heavy Cache Resource (Lazy Loading via @Getter(lazy=true))...");
        return "CACHE_DATA_LOADED";
    }

    @Value
    public static class AppInfo {
        String name;
        String version;
    }
}
