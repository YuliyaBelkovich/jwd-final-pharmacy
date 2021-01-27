package com.epam.jwd.dao.impl;

import com.epam.jwd.domain.Entity;
import com.epam.jwd.domain.Medicine;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.MedicineFactory;
import com.epam.jwd.criteria.Criteria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDao extends AbstractDao<Medicine> {

    private static MedicineDao instance = new MedicineDao();

    private static final String SELECT_ALL = "SELECT * FROM medicine";
    private static final String ADD = "INSERT INTO medicine (name, dose, recipe_requirement, information, price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE medicine SET name=?,dose=?,recipe_requirement=?,information=?,price=? WHERE id=?";
    private static final String DELETE = "DELETE FROM medicine WHERE id=?";
    private static final String SEARCH_BY_ID="SELECT * FROM medicine WHERE id = ?";

    private MedicineDao(){}

    public static MedicineDao getInstance() {
        return instance;
    }

    @Override
    public String getSelectQuery() {
        return SELECT_ALL;
    }

    public String getAddQuery(){
        return ADD;
    }

    public String getUpdateQuery(){
        return UPDATE;
    }

    public String getDeleteQuery(){
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
    public List<Medicine> parseResultSet(ResultSet rs) throws DAOException {
        List<Medicine> medicines = new ArrayList<>();
        try {
            while (rs.next()) {
                Medicine medicine = MedicineFactory.getInstance().create(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("dose"),
                        rs.getBoolean("recipe_requirement"),
                        rs.getString("information"),
                        rs.getDouble("price"));
                medicines.add(medicine);
            }
        } catch (SQLException | FactoryException er) {
            throw new DAOException(er.getMessage());
        }

        return medicines;
    }

    public void executeOperation(Medicine medicine, String operation) throws DAOException {
        try (PreparedStatement statement = retrieveConnection().prepareStatement(operation, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, medicine.getName());
            statement.setDouble(2, medicine.getDose());
            statement.setBoolean(3, medicine.isRecipeRequirement());
            statement.setString(4, medicine.getInfo());
            statement.setDouble(5, medicine.getPrice());
            try {
                statement.setInt(6, medicine.getId());
            } catch (SQLException e){}
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    medicine.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            throw new DAOException();
        }
    }


}
