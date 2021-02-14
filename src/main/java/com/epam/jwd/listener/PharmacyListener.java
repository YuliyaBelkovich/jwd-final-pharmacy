package com.epam.jwd.listener;

import com.epam.jwd.context.PharmacyContext;
import com.epam.jwd.pool.ConnectionPool;
import com.epam.jwd.util.GarbageCleaner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Class initiates the context and starts utility daemon threads
 * Closes connection pool when the application is killed
 */
@WebListener
public class PharmacyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PharmacyContext.getInstance().init();
        GarbageCleaner daemon = new GarbageCleaner(PharmacyContext.getInstance());
        daemon.setDaemon(true);
        daemon.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().closePool();
    }
}
