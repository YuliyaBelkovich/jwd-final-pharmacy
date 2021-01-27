package com.epam.jwd.dao.impl;

import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.factory.impl.UserFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {

    private static UserDao instance = new UserDao();

    private static final String SELECT_ALL = "SELECT * FROM user_pharmacy";
    private static final String ADD = "INSERT INTO user_pharmacy (name,e_mail,password,user_role,user_status) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE user_pharmacy SET name=?,e_mail=?,password=?, user_role = ?, user_status = ? WHERE id=?";
    private static final String DELETE = "DELETE FROM user_pharmacy WHERE id = ?";
    private static final String SEARCH_BY_ID = "SELECT * FROM user_pharmacy WHERE id = ?";

    private UserDao() {
    }

    public static UserDao getInstance() {
        return instance;
    }

    @Override
    public String getSelectQuery() {
        return SELECT_ALL;
    }

    @Override
    public String getAddQuery() {
        return ADD;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE;
    }

    @Override
    public String getSearchByIdQuery() {
        return SEARCH_BY_ID;
    }

    @Override
    public String getCriteriaQuery(Criteria<Entity> criteria) {
        return criteria.getQuery();
    }


    @Override
    public List<User> parseResultSet(ResultSet rs) throws DAOException {
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = UserFactory.getInstance().create(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("e_mail"),
                        rs.getString("password"),
                        rs.getString("user_role"),
                        rs.getString("user_status"));
                users.add(user);
            }
        } catch (SQLException | FactoryException exception) {
            throw new DAOException(exception.getMessage());
        }
        return users;
    }

    @Override
    public void executeOperation(User entity, String operation) throws DAOException {
        try (PreparedStatement statement = retrieveConnection().prepareStatement(operation, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setString(4, entity.getRole().getBaseName());
            statement.setString(5, entity.getStatus().getDbName());
            try {
                statement.setInt(6, entity.getId());
            } catch (SQLException e) {
            }
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException();
        }
    }
}
