package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("       SIMPLE & UNIFIED DEMO: SPRING CONFIG + ALL LOMBOK          ");

        User user1 = User.builder()
                .id(101L)
                .name("Dheemanth Reddy")
                .password("SecretPassword123") 
                .role("ADMIN")
                .role("DEVELOPER")
                .build();

        System.out.println("1. User Object (@Builder & @Singular & @Data):");
        System.out.println("   " + user1 + "\n");

        User user2 = user1.withEmail("dheemanth@example.com");
        System.out.println("2. Modified Copy (@With email='dheemanth@example.com'):");
        System.out.println("   " + user2 + "\n");

        System.out.println("3. Formatted Time (@UtilityClass): " + DateUtil.getCurrentFormattedTime() + "\n");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig.AppInfo appInfo = context.getBean(AppConfig.AppInfo.class);
        System.out.println("4. Immutable AppInfo (@Value): " + appInfo + "\n");

        AppConfig config = context.getBean(AppConfig.class);
        System.out.println("5. Accessing @Getter(lazy=true) field...");
        System.out.println("   Resource Value: " + config.getHeavyResource() + "\n");

        UserService userService = context.getBean(UserService.class);
        userService.registerUser(user2);
        userService.simulateDelay();

        context.close();

        System.out.println("                 SUCCESSFULLY COMPLETED!                          ");
    }
}
