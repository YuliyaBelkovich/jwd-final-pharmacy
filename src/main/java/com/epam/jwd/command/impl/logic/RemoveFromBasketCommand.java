package com.epam.jwd.command.impl.logic;

import com.epam.jwd.command.Basket;
import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.Medicine;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.impl.MedicineService;

public class RemoveFromBasketCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        String url = (String) requestContext.getSession().getAttribute("previousPage");
        if (requestContext.hasParameter("medicine_id")) {
            id = Integer.parseInt(requestContext.getParameter("medicine_id"));
        }
        Medicine medicine;
        try {
            medicine = MedicineService.getInstance().findById(id);
            Basket basket = Basket.getInstance();
            basket.removeFromBasket(medicine);
            requestContext.getSession().setAttribute("basket", basket);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Error", "Error accused while removing from basket");
            return () -> url;
        }
        return () -> url;
    }
}
