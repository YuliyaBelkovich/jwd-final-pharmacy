package com.epam.jwd.service.auth;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.AuthenticationException;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.entity.impl.UserService;
import com.epam.jwd.util.HashingUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LogInService {

    public static User logIn(String email, String password) throws AuthenticationException {
        try {
            password = HashingUtil.hash(password);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Criteria<User> criteria = UserCriteria.builder()
                .setEmail(email)
                .setPassword(password)
                .build();
        User user;
        try {
           user = UserService.getInstance().findByCriteria(criteria).get(0);
        } catch (DAOException | EntityNotFoundException e) {
            throw new AuthenticationException("Invalid e-mail or password");
        }
        return user;
    }
}
