package com.epam.jwd.service.entity.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.OrderCriteria;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.dao.impl.OrderDao;
import com.epam.jwd.domain.Order;
import com.epam.jwd.domain.OrderStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.factory.impl.OrderFactory;
import com.epam.jwd.service.entity.EntityService;
import com.epam.jwd.util.ValidationUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class OrderService implements EntityService<Order> {

    private static OrderService orderService;

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            return orderService = new OrderService();
        } else return orderService;
    }


    @Override
    public EntityDao<Order> getEntityDao() {
        EntityDao<Order> dao = OrderDao.getInstance();
        return dao;
    }

    @Override
    public CopyOnWriteArrayList<Order> getEntityCache() {
        return getApplicationContext().getCache(Order.class);
    }

    @Override
    public EntityFactory<Order> getEntityFactory() {
        return OrderFactory.getInstance();
    }

    @Override
    public Order createEntity(Object... args) throws FactoryException, DAOException, ValidationException, EntityNotFoundException {
        Order entity = getEntityFactory().create(args);
        entity.setOrderedMedicines((Map<Integer, Integer>) args[4]);
        ValidationUtil.validateEntity(entity);
        add(entity);
        return entity;
    }

    @Override
    public List<Order> findInCashByCriteria(Criteria<Order> criteria) {
        OrderCriteria orderCriteria = (OrderCriteria) criteria;
        return getEntityCache()
                .stream()
                .filter(order -> {
                    int id = orderCriteria.getId();
                    return id == 0 || id == order.getId();
                })
                .filter(order -> {
                    int id = orderCriteria.getPatientId();
                    return id == 0 || id == order.getPatientId();
                })
                .filter(order -> {
                    double price = orderCriteria.getPrice();
                    return price == 0 || price == order.getPrice();
                })
                .filter(order -> {
                    OrderStatus status = orderCriteria.getStatus();
                    return status == null || status == order.getStatus();
                })
                .collect(Collectors.toList());
    }
}
