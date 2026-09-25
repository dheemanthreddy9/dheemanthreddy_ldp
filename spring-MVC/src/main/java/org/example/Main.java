package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.example.config.WebConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();

        String baseDir = new File(".").getAbsolutePath();
        Context context = tomcat.addContext("", baseDir);

        AnnotationConfigWebApplicationContext appCtx = new AnnotationConfigWebApplicationContext();
        appCtx.setClassLoader(Main.class.getClassLoader());
        appCtx.register(WebConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(appCtx);
        org.apache.catalina.Wrapper wrapper = Tomcat.addServlet(context, "dispatcher", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcher");
        wrapper.setLoadOnStartup(1);

        System.out.println("Starting Spring MVC Application on http://localhost:" + port);
        tomcat.start();
        System.out.println("Spring MVC Application started successfully! Ready for Postman requests.");
        tomcat.getServer().await();
    }
}
