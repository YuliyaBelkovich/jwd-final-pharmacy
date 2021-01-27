package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.RecipeProlongationRequestFactory;
import com.epam.jwd.service.impl.RecipeProlongationRequestService;

public class UpdateRecipeProlongationRequestCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("Message", "");
        requestContext.getSession().setAttribute("Error", "");

        int id;
        int recipeId;
        int doctorId;
        String status;
        if (requestContext.hasParameter("request_id") && requestContext.hasParameter("request_recipe") &&
                requestContext.hasParameter("request_doctor") && requestContext.hasParameter("request_status")) {
            id = Integer.parseInt(requestContext.getParameter("request_id"));
            recipeId = Integer.parseInt(requestContext.getParameter("request_recipe"));
            doctorId = Integer.parseInt(requestContext.getParameter("request_doctor"));
            status = requestContext.getParameter("request_status");
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            requestContext.getSession().setAttribute("showHiddenScope", true);
            return () -> "/pharmacy?command=go_to_request_page&request_id=" + requestContext.getParameter("request_id");
        }
        RecipeProlongationRequest request;
        try {
            request = RecipeProlongationRequestFactory.getInstance().create(id, recipeId, doctorId, status);
            RecipeProlongationRequestService.getInstance().update(request);
        } catch (FactoryException | DAOException | EntityNotFoundException | ValidationException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            requestContext.getSession().setAttribute("showHiddenScope", true);
            return () -> "/pharmacy?command=go_to_request_page&request_id=" + id;
        }
        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Request updated!\n" + request.toString());
        requestContext.getSession().setAttribute("showHiddenScope", false);
        return () -> "/pharmacy?command=go_to_request_page&request_id=" + request.getId();
    }
}
