package com.epam.jwd.service.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.UserCriteria;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.dao.impl.UserDao;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.factory.impl.UserFactory;
import com.epam.jwd.service.EntityService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class UserService implements EntityService<User> {

    private static UserService userService;

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            return userService = new UserService();
        } else return userService;
    }

    @Override
    public EntityDao<User> getEntityDao() {
        EntityDao<User> dao = UserDao.getInstance();
        return dao;
    }

    @Override
    public CopyOnWriteArrayList<User> getEntityCache() {
        return getApplicationContext().getCache(User.class);
    }

    @Override
    public EntityFactory<User> getEntityFactory() {
        return UserFactory.getInstance();
    }

    @Override
    public List<User> findInCashByCriteria(Criteria<User> criteria) {
        UserCriteria userCriteria = (UserCriteria) criteria;
        return getEntityCache()
                .stream()
                .filter(user -> {
                    int id = userCriteria.getId();
                    return id == 0 || id == user.getId();
                })
                .filter(user -> {
                    String name = userCriteria.getName();
                    return name == null || user.getName().equals(name);
                })
                .filter(user -> {
                    String email = userCriteria.getEmail();
                    return email == null || user.getEmail().equals(email);
                })
                .filter(user -> {
                    String password = userCriteria.getPassword();
                    return password == null || user.getPassword().equals(password);
                })
                .filter(user -> {
                    Role role = userCriteria.getRole();
                    return role == null || user.getRole().equals(role);
                })
                .filter(user -> {
                    UserStatus status = userCriteria.getStatus();
                    return status == null || user.getStatus().equals(status);
                })
                .collect(Collectors.toList());
    }
}
