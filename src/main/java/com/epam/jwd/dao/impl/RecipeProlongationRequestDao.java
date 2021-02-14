package com.epam.jwd.dao.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.RecipeProlongationRequestFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementation of class {@link AbstractDao} parametrized with {@link RecipeProlongationRequest} class
 */
public class RecipeProlongationRequestDao extends AbstractDao<RecipeProlongationRequest> {

    private static RecipeProlongationRequestDao instance = new RecipeProlongationRequestDao();

    private static final String SELECT_ALL = "SELECT * FROM recipe_prolongation_requests";
    private static final String ADD = "INSERT INTO recipe_prolongation_requests (recipe_id,request_status,doctor_id) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE recipe_prolongation_requests SET recipe_id = ?, request_status = ?, doctor_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM recipe_prolongation_requests WHERE id = ?";
    private static final String SEARCH_BY_ID = "SELECT * FROM recipe_prolongation_requests WHERE id = ?";

private RecipeProlongationRequestDao(){}

    public static RecipeProlongationRequestDao getInstance() {
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
    public List<RecipeProlongationRequest> parseResultSet(ResultSet rs) throws DAOException {
        List<RecipeProlongationRequest> requests = new ArrayList<>();

        try {
            while (rs.next()) {
                RecipeProlongationRequest request = RecipeProlongationRequestFactory.getInstance().create(
                        rs.getInt("id"),
                        rs.getInt("recipe_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("request_status"));
                requests.add(request);
            }
        } catch (SQLException | FactoryException exception) {
            throw new DAOException(exception.getMessage());
        }

        return requests;
    }

    @Override
    public void executeOperation(RecipeProlongationRequest entity, String operation) throws DAOException {
        try (PreparedStatement statement = retrieveConnection().prepareStatement(operation,PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getRecipeId());
            statement.setString(2, entity.getStatus().getDbName());
            statement.setInt(3, entity.getDoctorId());
            try {
                statement.setInt(4, entity.getId());
            } catch (SQLException e){}
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Database issues");
        }
    }
}
