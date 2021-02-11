package com.epam.jwd.dao.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.AppointmentWindowFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AppointmentWindowDao extends AbstractDao<AppointmentWindow> {

    private static AppointmentWindowDao dao = new AppointmentWindowDao();

    private static final String SELECT_ALL = "SELECT * FROM doctor_timetable";
    private static final String ADD = "INSERT INTO doctor_timetable (doctor_id,status,date_time) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE doctor_timetable SET doctor_id = ?, status = ?, date_time = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM doctor_timetable WHERE id = ?";
    private static final String SEARCH_BY_ID = "SELECT * FROM doctor_timetable WHERE id = ?";

    private AppointmentWindowDao(){}

    public static AppointmentWindowDao getInstance() {
        return dao;
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
    public List<AppointmentWindow> parseResultSet(ResultSet rs) throws DAOException {
        List<AppointmentWindow> windows = new ArrayList<>();
        try {
            while (rs.next()) {
                AppointmentWindow window = AppointmentWindowFactory.getInstance().create(
                        rs.getInt("id"),
                        rs.getInt("doctor_id"),
                        rs.getTimestamp("date_time").toLocalDateTime(),
                        rs.getString("status")
                );
                windows.add(window);
            }
        } catch (SQLException | FactoryException exception) {
            throw new DAOException(exception.getMessage());
        }
        return windows;
    }

    @Override
    public void executeOperation(AppointmentWindow entity, String operation) throws DAOException {
        try (PreparedStatement statement = retrieveConnection().prepareStatement(operation, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getDoctorId());
            statement.setString(2, entity.getStatus().getDbName());
            statement.setTimestamp(3, Timestamp.valueOf(entity.getDateTime()));
            try {
                statement.setInt(4, entity.getId());
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
