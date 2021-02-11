package com.epam.jwd.service.auth;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.entity.EntityService;
import com.epam.jwd.service.entity.impl.UserService;
import com.epam.jwd.util.HashingUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegisterService {

    public static void register(String email, String password, String name, String role, String status) throws DAOException, FactoryException, EntityNotFoundException, ValidationException {
        //todo validation?

        try {
            password = HashingUtil.hash(password);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Criteria<User> criteria = UserCriteria.builder()
                .setEmail(email)
                .build();

        EntityService<User> service = UserService.getInstance();
        try {
            service.findByCriteria(criteria);
            throw new DAOException("User already exists");
        } catch (EntityNotFoundException e) {
            service.createEntity(0, name, email, password, role, status);
        }
    }
}
