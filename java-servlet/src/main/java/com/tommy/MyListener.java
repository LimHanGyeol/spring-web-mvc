package com.tommy;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Spring Web Mvc
 * Servlet Listener 와 Filter
 * Servlet Context LifeCycle 을 감지할 수 있는 Listener
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Initialized");
        sce.getServletContext().setAttribute("name", "hangyeol");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context Destroyed");
    }
}
