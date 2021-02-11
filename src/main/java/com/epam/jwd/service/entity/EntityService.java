package com.epam.jwd.service.entity;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.context.PharmacyContext;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.util.ValidationUtil;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public interface EntityService<T extends Entity> {

    default ApplicationContext getApplicationContext() {
        return PharmacyContext.getInstance();
    }

    EntityDao<T> getEntityDao();

    CopyOnWriteArrayList<T> getEntityCache();

    EntityFactory<T> getEntityFactory();

    List<T> findInCashByCriteria(Criteria<T> criteria);

    default List<T> findAll() throws DAOException{
        List<T> entities = getEntityCache();
        EntityDao<T> dao = getEntityDao();
        if (entities.isEmpty()) {
            entities = dao.getAll();
        }
        return entities;
    }

    default T findById(int id) throws EntityNotFoundException, DAOException {
        List<T> entities = getEntityCache();
        Optional<T> result;
        T entity;
        EntityDao<T> dao = getEntityDao();

        result = entities.stream()
                .filter(en -> en.getId() == id)
                .findAny();
        if (!result.isPresent()) {
            entity = dao.findEntityById(id);
        } else {
            entity = result.get();
        }
        return entity;
    }

    default void delete(T entity) throws DAOException {
        getApplicationContext().removeFromCache(entity);
        EntityDao<T> dao = getEntityDao();
        dao.delete(entity);
    }

    default void add(T entity) throws DAOException, ValidationException, EntityNotFoundException {
        EntityDao<T> dao = getEntityDao();
        dao.add(entity);
        getApplicationContext().addToCache(entity);
    }

    default void update(T entity) throws DAOException, EntityNotFoundException, ValidationException {
        EntityDao<T> dao = getEntityDao();
        ValidationUtil.validateEntity(entity);
        dao.update(entity);
        getApplicationContext().updateInCache(findById(entity.getId()), entity);
    }

    default List<T> findByCriteria(Criteria<T> criteria) throws EntityNotFoundException, DAOException {
        List<T> entities = findInCashByCriteria(criteria);
        EntityDao<T> dao = getEntityDao();
        if (entities.isEmpty()) {
            entities = dao.findByCriteria((Criteria<Entity>) criteria);
        }
        return entities;
    }

    default T createEntity(Object... args) throws FactoryException, DAOException, ValidationException, EntityNotFoundException {
        T entity = getEntityFactory().create(args);
        ValidationUtil.validateEntity(entity);
        add(entity);
        return entity;
    }

}
