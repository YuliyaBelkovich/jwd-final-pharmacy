package com.epam.jwd.context;

import com.epam.jwd.domain.Entity;

import java.util.concurrent.CopyOnWriteArrayList;

public interface ApplicationContext {
    void init();

    <T extends Entity> CopyOnWriteArrayList<T> getCache(Class<T> tClass);

    public <T extends Entity> void addToCache(T t);

    <T extends Entity> void removeFromCache(T t);

    <T extends Entity> void updateInCache(T oldT, T newT);

}
