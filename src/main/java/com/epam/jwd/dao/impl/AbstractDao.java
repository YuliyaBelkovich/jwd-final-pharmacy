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

    public abstract void executeOperation(T entity, String operation) throws DAOException;

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
        if (searchResult != null) {
            return searchResult.get(0);
        } else {
            throw new EntityNotFoundException();
        }
    }

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

    public void add(T entity) throws DAOException {
        getConnection();
        executeOperation(entity, getAddQuery());
        closeConnection();
    }

    public void update(T entity) throws DAOException {
        getConnection();
        executeOperation(entity, getUpdateQuery());
        closeConnection();
    }

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
