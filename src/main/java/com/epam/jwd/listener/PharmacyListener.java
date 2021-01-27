package com.epam.jwd.listener;

import com.epam.jwd.context.PharmacyContext;
import com.epam.jwd.util.GarbageDaemon;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PharmacyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PharmacyContext.getInstance().init();
        GarbageDaemon daemon = new GarbageDaemon(PharmacyContext.getInstance());
        daemon.setDaemon(true);
        daemon.start();
        System.out.println("keke");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
