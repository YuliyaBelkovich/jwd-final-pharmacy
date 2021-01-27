package com.epam.jwd.dao.impl;

import com.epam.jwd.domain.BankAccount;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.BankAccountFactory;
import com.epam.jwd.criteria.Criteria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDao extends AbstractDao<BankAccount> {

    private static BankAccountDao instance = new BankAccountDao();

    private static final String SELECT_ALL = "SELECT * FROM patient_bank_account";
    private static final String ADD = "INSERT INTO patient_bank_account (patient_id,IBAN,card_holder,cvv, expiration_date) VALUES (?,?,?,?,?)";
    private static final String UPDATE = "UPDATE patient_bank_account SET patient_id = ?, IBAN = ?,card_holder = ?,cvv = ?, expiration_date = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM patient_bank_account WHERE id = ?";
    private static final String SEARCH_BY_ID = "SELECT * FROM patient_bank_account WHERE id = ?";

    private BankAccountDao() {
    }

    public static BankAccountDao getInstance() {
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
    public List<BankAccount> parseResultSet(ResultSet rs) throws DAOException {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try {
            while (rs.next()) {
                BankAccount bankAccount = BankAccountFactory.getInstance().create(rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getString("IBAN"),
                        rs.getString("card_holder"),
                        rs.getString("expiration_date"),
                        rs.getInt("cvv"));
                bankAccounts.add(bankAccount);
            }
        } catch (SQLException | FactoryException exception) {
            throw new DAOException(exception.getMessage());
        }
        return bankAccounts;
    }

    @Override
    public void executeOperation(BankAccount entity, String operation) throws DAOException {
        try (PreparedStatement statement = retrieveConnection().prepareStatement(operation, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getPatientId());
            statement.setString(2, entity.getIBAN());
            statement.setString(3, entity.getCardHolder());
            statement.setInt(4, entity.getCvv());
            statement.setString(5, entity.getExpirationDate());
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
