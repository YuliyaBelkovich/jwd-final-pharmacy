package com.epam.jwd.context;

import com.epam.jwd.domain.ApplicationProperties;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.pool.ConnectionPool;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Implementation of {@link ApplicationContext} class
 * Contains custom properties for this app
 */
public class PharmacyContext implements ApplicationContext {

    private static PharmacyContext pharmacyContext = new PharmacyContext();
    private static PharmacyCache pharmacyCacheContext = PharmacyCache.getInstance();
    private static ApplicationProperties applicationProperties;
    private static ConnectionPool connectionPool;
    private static final String PROPERTIES_FILE_NAME = "C:/Users/Asus/IdeaProjects/jwd-final-pharmacy/src/main/resources/application.properties";

    private PharmacyContext() {
    }

    public static PharmacyContext getInstance() {
        return pharmacyContext;
    }

    public void init() {
        ApplicationProperties.setPropertiesFileName(PROPERTIES_FILE_NAME);
        applicationProperties = ApplicationProperties.getInstance();
        connectionPool = ConnectionPool.getInstance();
        try {
            pharmacyCacheContext.initCache();
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    public <T extends Entity> CopyOnWriteArrayList<T> getCache(Class<T> tClass) {
        return (CopyOnWriteArrayList<T>) pharmacyCacheContext.getCache(tClass.getName());
    }

    public <T extends Entity> void addToCache(T t) {
        pharmacyCacheContext.addToCache(t);
    }

    public <T extends Entity> void removeFromCache(T t) {
        pharmacyCacheContext.removeFromCache(t);
    }

    public <T extends Entity> void updateInCache(T oldT, T newT) {
        pharmacyCacheContext.updateInCache(oldT, newT);
    }

    public ApplicationProperties getApplicationProperties() {
        return applicationProperties;
    }
}
