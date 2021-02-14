package com.epam.jwd.command.impl.entity.payment;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.PageName;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.PaymentCriteria;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.Payment;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.entity.impl.PaymentService;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Class responsible for searching {@link Payment} objects according to given criteria
 * Returns error message when payment not found
 */
public class SearchPaymentCommand implements Command {
    private static final ResponseContext SEARCH_RESULT = PageName.SEARCH_PAYMENT_PAGE::getJspFileName;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        String IBAN = null;
        double sum = 0;
        LocalDateTime dateTime = null;
        if (requestContext.hasParameter("payment_id")) {
            id = Integer.parseInt(requestContext.getParameter("payment_id"));
        }
        if (requestContext.hasParameter("payment_iban")) {
            IBAN = requestContext.getParameter("payment_iban");
        }
        if (requestContext.hasParameter("payment_sum")) {
            sum = Double.parseDouble(requestContext.getParameter("payment_sum"));
        }
        if (requestContext.hasParameter("payment_date")) {
            dateTime = LocalDateTime.parse(requestContext.getParameter("payment_date"));
        }
        Criteria<Payment> criteria = PaymentCriteria.builder()
                .id(id)
                .setSum(sum)
                .setIBAN(IBAN)
                .setDateTime(dateTime)
                .build();
        List<Payment> payments;

        try {
            payments = PaymentService.getInstance().findByCriteria(criteria);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Error", "Payment not found");
            return SEARCH_RESULT;
        }
        requestContext.setAttribute("Payment", payments);
        requestContext.getSession().setAttribute("previousPage", requestContext.getUrl());
        return SEARCH_RESULT;
    }
}
