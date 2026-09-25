package org.example;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private int processCount = 0;

    @Synchronized 
    public void registerUser(@NonNull User user) {
        log.info("[{}] Registering user: {}", DateUtil.getCurrentFormattedTime(), user.getName());
        processCount++;
        log.info("Total users processed so far: {}", processCount);
    }

    @SneakyThrows 
    public void simulateDelay() {
        log.info("Simulating delay via @SneakyThrows...");
        Thread.sleep(200);
    }
}
