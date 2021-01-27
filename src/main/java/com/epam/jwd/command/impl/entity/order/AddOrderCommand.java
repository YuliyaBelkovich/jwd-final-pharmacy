package com.epam.jwd.command.impl.entity.order;

import com.epam.jwd.command.Basket;
import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.Medicine;
import com.epam.jwd.domain.Order;
import com.epam.jwd.domain.OrderStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.OrderService;

import java.util.HashMap;
import java.util.Map;

public class AddOrderCommand implements Command {
    private static final ResponseContext ADD_ORDER_FAIL = () -> "/pharmacy?command=go_to_basket_page";
    private static final ResponseContext ADD_ORDER_SUCCESS = () -> "/pharmacy?command=go_to_add_payment_page";

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int patientId;
        if (requestContext.hasParameter("order_patient")) {
            patientId = Integer.parseInt(requestContext.getParameter("order_patient"));
        } else {
            patientId = 0;
        }
        Map<Medicine, Integer> basket = Basket.getInstance().getBasketMedicine();
        double totalPrice = Basket.getInstance().getTotalPrice();
        Map<Integer, Integer> orderedMedicines = new HashMap<>();
        basket.forEach((medicine, integer) -> orderedMedicines.put(medicine.getId(), integer));
        Order order;
        try {
            order = OrderService.getInstance().createEntity(0, totalPrice, patientId, "NOT PAID", orderedMedicines);
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage() + " error");
            return ADD_ORDER_FAIL;
        }
        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("order", order);
        requestContext.getSession().setAttribute("previousPage",requestContext.getUrl());
        requestContext.getSession().setAttribute("Message", "Order created!\n");
        return ADD_ORDER_SUCCESS;
    }
}
