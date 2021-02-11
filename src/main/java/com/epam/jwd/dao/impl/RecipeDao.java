package com.epam.jwd.dao.impl;

import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.Recipe;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.RecipeFactory;
import com.epam.jwd.criteria.Criteria;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao extends AbstractDao<Recipe> {

    private static RecipeDao instance = new RecipeDao();
    private static final Logger logger = Logger.getLogger(RecipeDao.class);

    private static final String SELECT_ALL = "SELECT * FROM recipe";
    private static final String ADD = "INSERT INTO recipe (dose,patient_id,medicine_id,doctor_id,duration,date) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE recipe SET dose = ?, patient_id = ?, medicine_id = ?, doctor_id = ?, duration =?, date = ? WHERE id =?";
    private static final String DELETE = "DELETE FROM recipe WHERE id = ?";
    private static final String SEARCH_BY_ID = "SELECT * FROM recipe WHERE id = ?";

    private RecipeDao() {
    }

    public static RecipeDao getInstance() {
        return instance;
    }

    @Override
    public String getSelectQuery() {
        return SELECT_ALL;
    }

    public String getAddQuery() {
        return ADD;
    }

    public String getUpdateQuery() {
        return UPDATE;
    }

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
    public List<Recipe> parseResultSet(ResultSet rs) throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        try {
            while (rs.next()) {
                Recipe recipe = RecipeFactory.getInstance().create(rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("medicine_id"),
                        rs.getDouble("dose"),
                        rs.getInt("duration"),
                        rs.getInt("doctor_id"),
                        rs.getDate("date").toLocalDate());
                recipes.add(recipe);
            }
        } catch (SQLException | FactoryException exception) {
            throw new DAOException();
        }
        return recipes;
    }

    @Override
    public void executeOperation(Recipe entity, String operation) throws DAOException {
        try (PreparedStatement statement = retrieveConnection().prepareStatement(operation, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, entity.getDose());
            statement.setInt(2, entity.getPatientId());
            statement.setInt(3, entity.getMedicineId());
            statement.setInt(4, entity.getDoctorId());
            statement.setInt(5, entity.getDuration());
            statement.setDate(6, Date.valueOf(entity.getDate()));
            try {
                statement.setInt(7, entity.getId());
            } catch (SQLException e) {
            }
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
