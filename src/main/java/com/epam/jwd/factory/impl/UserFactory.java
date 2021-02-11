package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.EntityFactory;
import org.apache.log4j.Logger;

public class UserFactory implements EntityFactory<User> {

    private static UserFactory userFactory = new UserFactory();
    private static final Logger logger = Logger.getLogger(UserFactory.class);

    private UserFactory() {
    }

    public static UserFactory getInstance() {
        return userFactory;
    }

    @Override
    public User create(Object... args) throws FactoryException {
        User user;
        try {
            user = new User((int) args[0],
                    (String) args[1],
                    (String) args[2],
                    (String) args[3],
                    Role.resolveRoleByDBName((String) args[4]),
                    UserStatus.resolveStatusByDBName((String) args[5])
            );
        } catch (ClassCastException e) {
            logger.error(e.getMessage());
            throw new FactoryException("Wrong arguments while creating the User object");
        }
        return user;
    }
}
