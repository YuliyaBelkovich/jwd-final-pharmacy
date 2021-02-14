package com.epam.jwd.command.impl.entity.payment;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.Order;
import com.epam.jwd.domain.OrderStatus;
import com.epam.jwd.domain.Payment;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.entity.impl.OrderService;
import com.epam.jwd.service.entity.impl.PaymentService;
import com.epam.jwd.service.mail.MailService;

import java.time.LocalDateTime;

/**
 * Command responsible for adding new {@link com.epam.jwd.domain.Payment}
 * Returns error message if mandatory field is missing or if the error occur on the inner levels
 * Sets {@link OrderStatus} to PAID if the payment object created successfully
 */
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
        Payment payment;
        try {
            payment = PaymentService.getInstance().createEntity(0, sum, iban, dateTime);
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            return () -> "/pharmacy?command=go_to_add_payment_page&error=" + e.getMessage().replace(" ", "+");
        }
        Order order = (Order) requestContext.getSession().getAttribute("order");
        order.setStatus(OrderStatus.PAID);
        try {
            OrderService.getInstance().update(order);
        } catch (DAOException | ValidationException | EntityNotFoundException e) {
            return () -> "/pharmacy?command=go_to_add_payment_page&error=" + e.getMessage().replace(" ", "+");
        }
        if (requestContext.getSession().getAttribute("user_email") != null) {
            MailService.getInstance().sendPDFEmail((String) requestContext.getSession().getAttribute("user_email"), "Payment invoice", "Payment succeeded! Here's your invoice, please wait till call-center operator will contact you", "Payment ID: " + payment.getId() + "\n" + "IBAN: " + payment.getIBAN() + "\n" + "Sum: " + payment.getSum() + "\n" + "Date: " + payment.getDateTime());
        }
        requestContext.getSession().setAttribute("basket", null);
        requestContext.getSession().setAttribute("basketPrice", null);
        return ADD_PAYMENT_SUCCESS;
    }
}
