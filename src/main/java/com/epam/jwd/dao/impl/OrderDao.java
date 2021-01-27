package com.epam.jwd.dao.impl;

import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.Order;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.OrderFactory;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.pool.ConnectionProxy;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao extends AbstractDao<Order> {

    private static OrderDao instance = new OrderDao();

    private static final String SELECT_ALL = "SELECT * FROM medicine_order";
    private static final String SEARCH_BY_ID_ALL_ORDERED_MEDICINE = "SELECT * FROM ordered_medicines WHERE order_id = ?";
    private static final String ADD = "INSERT INTO medicine_order (price, patient_id,order_status) VALUES (?,?,?)";
    private static final String ADD_TO_ORDERED_MEDICINE = "INSERT INTO ordered_medicines (medicine_id,amount,order_id) VALUES (?,?,?)";
    private static final String UPDATE = "UPDATE medicine_order SET price = ?, patient_id = ?, order_status = ? WHERE id =?";
    private static final String DELETE = "DELETE FROM medicine_order WHERE id=?";
    private static final String DELETE_FROM_MEDICINES = "DELETE FROM ordered_medicines WHERE order_id=?";
    private static final String SEARCH_BY_ID = "SELECT * FROM medicine_order WHERE id = ?";

    private OrderDao() {
    }

    public static OrderDao getInstance() {
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
    public List<Order> parseResultSet(ResultSet rs) throws DAOException {
        List<Order> orders = new ArrayList<>();
        try {
            while (rs.next()) {
                Order order = OrderFactory.getInstance().create(rs.getInt("id"),
                        rs.getDouble("price"),
                        rs.getInt("patient_id"),
                        rs.getString("order_status")
                );
                orders.add(order);
            }
        } catch (SQLException | FactoryException exception) {
            throw new DAOException(exception.getMessage());
        }
        return orders;
    }

    private Map<Integer, Integer> parseMedicineResultSet(ResultSet rs) throws DAOException {
        Map<Integer, Integer> medicineIdList = new HashMap<>();
        try {
            while (rs.next()) {
                medicineIdList.put(rs.getInt("medicine_id"), rs.getInt("amount"));
            }
        } catch (SQLException exception) {
            throw new DAOException(exception.getMessage());
        }
        return medicineIdList;
    }


    public void delete(Order order) throws DAOException {
        Connection connection = getConnection();
        PreparedStatement orderStatement;
        PreparedStatement medicineStatement;
        try {
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(DELETE);
            orderStatement.setInt(1, order.getId());
            orderStatement.executeUpdate();
            try {
                orderStatement.close();
            } catch (SQLException exception) {
            }
            medicineStatement = connection.prepareStatement(DELETE_FROM_MEDICINES);
            medicineStatement.setInt(1, order.getId());
            medicineStatement.executeUpdate();
            try {
                medicineStatement.close();
            } catch (SQLException exception) {
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new DAOException();
            }
            throw new DAOException();
        } finally {
            closeConnection();
        }
    }

    public List<Order> findByCriteria(Criteria<Entity> criteria) throws DAOException {
        Connection connection = getConnection();
        PreparedStatement orderStatement;
        PreparedStatement medicineStatement;
        List<Order> orders;
        try {
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(criteria.getQuery());
            ResultSet rs = orderStatement.executeQuery();
            orders = parseResultSet(rs);
            try {
                orderStatement.close();
            } catch (SQLException exception) {
            }
            for (Order order : orders) {
                medicineStatement = connection.prepareStatement(SEARCH_BY_ID_ALL_ORDERED_MEDICINE);
                medicineStatement.setInt(1, order.getId());
                ResultSet resultSet = medicineStatement.executeQuery();
                order.setOrderedMedicines(parseMedicineResultSet(resultSet));
                try {
                    medicineStatement.close();
                } catch (SQLException exception) {
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new DAOException();
            }
            throw new DAOException();
        } finally {
            closeConnection();
        }
        return orders;
    }

    public Order findEntityById(int id) throws DAOException {
        Connection connection = getConnection();
        PreparedStatement orderStatement;
        PreparedStatement medicineStatement;
        Order order;
        try {
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(SEARCH_BY_ID);
            orderStatement.setInt(1, id);
            ResultSet rs = orderStatement.executeQuery();
            order = parseResultSet(rs).get(0);
            try {
                orderStatement.close();
            } catch (SQLException exception) {
            }
            medicineStatement = connection.prepareStatement(SEARCH_BY_ID_ALL_ORDERED_MEDICINE);
            medicineStatement.setInt(1, order.getId());
            order.setOrderedMedicines(parseMedicineResultSet(medicineStatement.executeQuery()));
            try {
                medicineStatement.close();
            } catch (SQLException exception) {
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new DAOException();
            }
            throw new DAOException();
        } finally {
            closeConnection();
        }
        return order;
    }

    public List<Order> getAll() throws DAOException {
        Connection connection = getConnection();
        PreparedStatement orderStatement;
        PreparedStatement medicineStatement;
        List<Order> orders;
        try {
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = orderStatement.executeQuery();
            orders = parseResultSet(rs);
            try {
                orderStatement.close();
            } catch (SQLException exception) {
            }
            for (Order order : orders) {
                medicineStatement = connection.prepareStatement(SEARCH_BY_ID_ALL_ORDERED_MEDICINE);
                medicineStatement.setInt(1, order.getId());
                ResultSet resultSet = medicineStatement.executeQuery();
                order.setOrderedMedicines(parseMedicineResultSet(resultSet));
                try {
                    medicineStatement.close();
                } catch (SQLException exception) {
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new DAOException();
            }
            throw new DAOException();
        } finally {
            closeConnection();
        }
        return orders;
    }


    public void add(Order entity) throws DAOException {
        Connection connection = getConnection();
        PreparedStatement orderStatement;
        PreparedStatement medicineStatement;
        try {
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(ADD, PreparedStatement.RETURN_GENERATED_KEYS);
            orderStatement.setDouble(1, entity.getPrice());
            if (entity.getPatientId() != 0) {
                orderStatement.setInt(2, entity.getPatientId());
            } else {
                orderStatement.setNull(2, Types.INTEGER);
            }
            orderStatement.setString(3, entity.getStatus().getDbName());
            orderStatement.executeUpdate();
            try (ResultSet generatedKeys = orderStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
            }
            try {
                orderStatement.close();
            } catch (SQLException exception) {
            }
            for (int medicineId : entity.getOrderedMedicines().keySet()) {
                medicineStatement = connection.prepareStatement(ADD_TO_ORDERED_MEDICINE);
                medicineStatement.setInt(1, medicineId);
                medicineStatement.setInt(2, entity.getOrderedMedicines().get(medicineId));
                medicineStatement.setInt(3, entity.getId());
                medicineStatement.executeUpdate();
                try {
                    medicineStatement.close();
                } catch (SQLException exception) {
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new DAOException();
            }
            throw new DAOException();
        } finally {
            closeConnection();
        }
    }

    public void update(Order entity) throws DAOException {
        Connection connection = getConnection();
        PreparedStatement orderStatement;
        try {
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(UPDATE);
            orderStatement.setDouble(1, entity.getPrice());
            if (entity.getPatientId() != 0) {
                orderStatement.setInt(2, entity.getPatientId());
            } else {
                orderStatement.setNull(2, Types.INTEGER);
            }
            orderStatement.setString(3, entity.getStatus().getDbName());
            orderStatement.setInt(4, entity.getId());
            orderStatement.executeUpdate();
            try {
                orderStatement.close();
            } catch (SQLException exception) {
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new DAOException(ex.getMessage());
            }
            throw new DAOException(e.getMessage());
        } finally {
            closeConnection();
        }
    }

    @Override
    public void executeOperation(Order entity, String operation) throws DAOException {
    }
}
