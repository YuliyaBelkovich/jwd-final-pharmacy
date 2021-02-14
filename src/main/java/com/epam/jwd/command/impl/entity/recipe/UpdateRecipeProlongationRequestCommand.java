package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.RecipeProlongationRequestFactory;
import com.epam.jwd.service.entity.impl.RecipeProlongationRequestService;
/**
 * Class responsible for updating the {@link RecipeProlongationRequest} object
 * Requires all fields to update
 */
public class UpdateRecipeProlongationRequestCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {

        int id;
        int recipeId;
        int doctorId;
        String status;
        String url = (String) requestContext.getSession().getAttribute("previousPage");
        if (requestContext.hasParameter("request_id") && requestContext.hasParameter("request_recipe") &&
                requestContext.hasParameter("request_doctor") && requestContext.hasParameter("request_status")) {
            id = Integer.parseInt(requestContext.getParameter("request_id"));
            recipeId = Integer.parseInt(requestContext.getParameter("request_recipe"));
            doctorId = Integer.parseInt(requestContext.getParameter("request_doctor"));
            status = requestContext.getParameter("request_status");
        } else {
            return () -> url+"error=Missing+mandatory+field";
        }
        RecipeProlongationRequest request;
        try {
            request = RecipeProlongationRequestFactory.getInstance().create(id, recipeId, doctorId, status);
            RecipeProlongationRequestService.getInstance().update(request);
        } catch (FactoryException | DAOException | EntityNotFoundException | ValidationException e) {
            return () -> url + "&error=" + e.getMessage().replace(" ", "+");
        }
        return () -> url + "&message=Request+updated!";
    }
}
