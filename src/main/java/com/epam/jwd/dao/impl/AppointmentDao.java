package com.epam.jwd.dao.impl;

import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.AppointmentFactory;
import com.epam.jwd.criteria.Criteria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao extends AbstractDao<Appointment> {

    private static AppointmentDao instance = new AppointmentDao();

    private static final String SELECT_ALL = "SELECT * FROM appointment";
    private static final String ADD = "INSERT INTO appointment (patient_id, doctor_id, date_time, info, status) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE appointment SET patient_id=?, doctor_id=?, date_time=?,status=? WHERE id=?";
    private static final String DELETE = "DELETE FROM appointment WHERE id=?";
    private static final String SEARCH_BY_ID = "SELECT * FROM appointment WHERE id= ?";

    private AppointmentDao() {
    }

    public static AppointmentDao getInstance() {
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

    public String getCriteriaQuery(Criteria<Entity> criteria) {
        return criteria.getQuery();
    }

    @Override
    public List<Appointment> parseResultSet(ResultSet rs) throws DAOException {
        List<Appointment> appointments = new ArrayList<>();
        try {
            while (rs.next()) {
                Appointment appointment = AppointmentFactory.getInstance().create(rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getTimestamp("date_time").toLocalDateTime(),
                        rs.getString("info"),
                        rs.getString("status"));
                appointments.add(appointment);
            }
        } catch (SQLException | FactoryException exception) {
            throw new DAOException(exception.getMessage());
        }
        return appointments;
    }

    @Override
    public void executeOperation(Appointment appointment, String operation) throws DAOException {
        try (PreparedStatement statement = retrieveConnection().prepareStatement(operation, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setTimestamp(3, Timestamp.valueOf(appointment.getDateTime()));
            statement.setString(4, appointment.getInfo());
            statement.setString(5, appointment.getAppointmentStatus().getDbName());
            try {
                statement.setInt(6, appointment.getId());
            } catch (SQLException e){}

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    appointment.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Database issues");
        }
    }


}
