package com.epam.jwd.command.impl.entity.payment;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.Order;
import com.epam.jwd.domain.OrderStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.entity.impl.OrderService;
import com.epam.jwd.service.entity.impl.PaymentService;

import java.time.LocalDateTime;

public class AddPaymentCommand implements Command {

    private static final ResponseContext ADD_PAYMENT_SUCCESS = () -> "/pharmacy?command=go_to_payment_success_page";

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        double sum;
        String iban;
        if (requestContext.hasParameter("payment_sum") && requestContext.hasParameter("payment_iban")) {
            sum = Double.parseDouble(requestContext.getParameter("payment_sum"));
            iban = requestContext.getParameter("payment_iban");
        } else {
            return () -> "/pharmacy?command=go_to_add_payment_page&error=Missing+mandatory+field";
        }
        LocalDateTime dateTime = LocalDateTime.now();

        try {
            PaymentService.getInstance().createEntity(0, sum, iban, dateTime);
            //todo if validation or entity not found = message, if dao or factory - server error page
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            return () -> "/pharmacy?command=go_to_add_payment_page&error=" + e.getMessage().replace(" ", "+");
        }
        Order order = (Order) requestContext.getSession().getAttribute("order");
        order.setStatus(OrderStatus.PAID);
        try {
            OrderService.getInstance().update(order);
        } catch (DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            return () -> "/pharmacy?command=go_to_add_payment_page&error=" + e.getMessage().replace(" ", "+");
        }
        //todo mail
        requestContext.getSession().setAttribute("basket",null);
        requestContext.getSession().setAttribute("basketPrice",null);
        return ADD_PAYMENT_SUCCESS;
    }
}
