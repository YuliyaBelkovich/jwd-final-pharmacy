package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.Medicine;
import com.epam.jwd.domain.OrderStatus;
import com.epam.jwd.exception.UnknownEntityException;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.domain.Order;
import com.epam.jwd.exception.FactoryException;

import java.util.List;

public class OrderFactory implements EntityFactory<Order> {

    private static OrderFactory orderFactory = new OrderFactory();

    private OrderFactory(){};

    public static OrderFactory getInstance() {
        return orderFactory;
    }

    @Override
    public Order create(Object... args) throws FactoryException {
        Order order;
        try {
            order = new Order((int) args[0],
                    (double) args[1],
                    (int) args[2],
                    OrderStatus.resolveStatusByDBName((String) args[3]));
        } catch (ClassCastException | UnknownEntityException e){
            throw new FactoryException("Wrong arguments while creating the Order object");
        }
        return order;
    }
}
