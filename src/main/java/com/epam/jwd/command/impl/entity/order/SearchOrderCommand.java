package com.epam.jwd.command.impl.entity.order;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.PageName;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.OrderCriteria;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.Order;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.entity.impl.OrderService;

import java.util.List;
/**
 * Class responsible for searching the {@link Order}
 * Returns error message when order window not found
 */
public class SearchOrderCommand implements Command {

    private static final ResponseContext SEARCH_RESULT = PageName.SEARCH_ORDER_PAGE::getJspFileName;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        int patientId = 0;
        double price = 0;
        if (requestContext.hasParameter("order_id")) {
            id = Integer.parseInt(requestContext.getParameter("order_id"));
        }
        if (requestContext.hasParameter("order_patient")) {
            patientId = Integer.parseInt(requestContext.getParameter("order_patient"));
        }
        if (requestContext.hasParameter("order_price")) {
            price = Double.parseDouble(requestContext.getParameter("order_price"));
        }
        Criteria<Order> criteria = OrderCriteria.builder()
                .id(id)
                .setPatientId(patientId)
                .setPrice(price)
                .build();

        List<Order> orderList;
        try {
            orderList = OrderService.getInstance().findByCriteria(criteria);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Error", "Order not found");
            return SEARCH_RESULT;
        }
        requestContext.setAttribute("Order", orderList);
        requestContext.getSession().setAttribute("previousPage", requestContext.getUrl());
        return SEARCH_RESULT;
    }
}
