package com.epam.jwd.dao.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.Payment;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.PaymentFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementation of class {@link AbstractDao} parametrized with {@link Payment} class
 */
public class PaymentDao extends AbstractDao<Payment> {

    private static PaymentDao instance = new PaymentDao();

    private static final String SELECT_ALL = "SELECT * FROM payment";
    private static final String ADD = "INSERT INTO payment (sum, IBAN, date_time) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE payment SET sum = ?, IBAN = ?, date_time = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM payment WHERE id = ?";
    private static final String SEARCH_BY_ID = "SELECT * FROM payment WHERE id = ?";

    private PaymentDao(){}

    public static PaymentDao getInstance() {
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
    public List<Payment> parseResultSet(ResultSet rs) throws DAOException {
        List<Payment> payments = new ArrayList<>();
        try {
            while (rs.next()) {
                Payment payment = PaymentFactory.getInstance().create(
                        rs.getInt("id"),
                        rs.getDouble("sum"),
                        rs.getString("IBAN"),
                        rs.getTimestamp("date_time").toLocalDateTime()
                );
                payments.add(payment);
            }
        } catch (SQLException | FactoryException er) {
            throw new DAOException(er.getMessage());
        }
        return payments;
    }

    @Override
    public void executeOperation(Payment entity, String operation) throws DAOException {
        try (PreparedStatement statement = retrieveConnection().prepareStatement(operation,PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setDouble(1, entity.getSum());
            statement.setString(2, entity.getIBAN());
            statement.setTimestamp(3, Timestamp.valueOf(entity.getDateTime()));
            try {
                statement.setInt(4, entity.getId());
            } catch (SQLException e){}
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            throw new DAOException("Database issues");
        }
    }
}
