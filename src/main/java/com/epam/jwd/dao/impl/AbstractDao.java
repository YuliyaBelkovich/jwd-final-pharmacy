package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class with database access functions
 *
 * @param <T>
 */
public abstract class AbstractDao<T extends Entity> implements EntityDao<T> {

    private static Logger logger = Logger.getLogger(AbstractDao.class);

    private Connection connection;

    public Connection retrieveConnection() {
        return connection;
    }

    public Connection getConnection() {
        return connection = ConnectionPool.getInstance().retrieveConnection();
    }

    public abstract String getSelectQuery();

    public abstract String getAddQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract String getSearchByIdQuery();

    public abstract String getCriteriaQuery(Criteria<Entity> criteria);

    public abstract List<T> parseResultSet(ResultSet rs) throws DAOException;

    /**
     * Method fills in the prepared statement of operations ADD or UPDATE
     * @param entity - <T> object to update or add to database
     * @param operation -ADD or UPDATE
     * @throws DAOException - when transaction failed due to the SQL error
     */
    public abstract void executeOperation(T entity, String operation) throws DAOException;

    /**
     * Method finds entity <T> by certain criteria, <T> must implement interface Entity
     *
     * @param criteria
     * @return List<T>
     * @throws DAOException            - when transaction failed due to the SQL errors
     * @throws EntityNotFoundException - when entity with given criteria is absent
     */
    public List<T> findByCriteria(Criteria<Entity> criteria) throws DAOException, EntityNotFoundException {
        getConnection();
        List<T> searchResult;
        try (PreparedStatement statement = connection.prepareStatement(getCriteriaQuery(criteria))) {
            ResultSet rs = statement.executeQuery();
            searchResult = parseResultSet(rs);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new DAOException("Database issues. Please, try later.");
        }
        closeConnection();
        if (!searchResult.isEmpty()) {
            return searchResult;
        } else {
            throw new EntityNotFoundException();
        }

    }

    /**
     * Method finds entity by it's id
     *
     * @param id
     * @return object T - entity
     * @throws DAOException            - when transaction failed due to the SQL errors
     * @throws EntityNotFoundException - when entity with given criteria is absent
     */
    public T findEntityById(int id) throws DAOException, EntityNotFoundException {
        getConnection();
        List<T> searchResult;
        try (PreparedStatement statement = connection.prepareStatement(getSearchByIdQuery())) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            searchResult = parseResultSet(rs);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new DAOException("Database issues. Please, try later.");
        }
        closeConnection();
        if (!searchResult.isEmpty()) {
            return searchResult.get(0);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Deletes entity from database, returns void
     * @param entity
     * @throws DAOException - when transaction failed due to the SQL errors
     */
    public void delete(T entity) throws DAOException {
        getConnection();
        try (PreparedStatement statement = connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new DAOException("Database issues. Please, try later.");
        }
        closeConnection();
    }

    /**
     * Adds entity to database
     * @param entity
     * @throws DAOException - when transaction failed due to the SQL errors
     */
    public void add(T entity) throws DAOException {
        getConnection();
        executeOperation(entity, getAddQuery());
        closeConnection();
    }

    /**
     * Updates all the fields of entity in database
     * @param entity
     * @throws DAOException - when transaction failed due to the SQL errors
     */
    public void update(T entity) throws DAOException {
        getConnection();
        executeOperation(entity, getUpdateQuery());
        closeConnection();
    }

    /**
     * Finds all present entities in database
     * @return
     * @throws DAOException
     */
    public List<T> getAll() throws DAOException {
        getConnection();
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database issues. Please, try later.");
        }
        closeConnection();
        return list;
    }

    /**
     * Returns {@link Connection} to the connection pool
     * @throws DAOException
     */
    public void closeConnection() throws DAOException {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Database issues. Please, try later.");
        }
        connection = null;
    }
}
