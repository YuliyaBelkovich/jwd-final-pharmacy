package com.epam.jwd.dao;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EntityDao <T>{

    String getSelectQuery();

    String getAddQuery();

    String getUpdateQuery();

    String getDeleteQuery();

    String getSearchByIdQuery();

    List<T> parseResultSet(ResultSet rs) throws DAOException;

    void executeOperation(T entity, String operation) throws DAOException;

    List<T> findByCriteria(Criteria<Entity> criteria) throws DAOException, EntityNotFoundException;

    T findEntityById(int id) throws DAOException, EntityNotFoundException;

    void delete(T entity) throws DAOException;

    void add(T entity) throws DAOException;

    void update(T entity) throws DAOException;

    List<T> getAll() throws DAOException;

}
