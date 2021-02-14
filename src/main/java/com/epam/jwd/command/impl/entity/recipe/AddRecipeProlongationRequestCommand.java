package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.entity.impl.RecipeProlongationRequestService;
import com.epam.jwd.service.entity.impl.RecipeService;

/**
 * Command responsible for adding new {@link com.epam.jwd.domain.RecipeProlongationRequest}
 * Returns error message if mandatory field is missing or if the error occur on the inner levels
 */
public class AddRecipeProlongationRequestCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int recipeId;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("request_recipe")) {
            recipeId = Integer.parseInt(requestContext.getParameter("request_recipe"));
        } else {
            return () -> url + "&error=Missing+mandatory+field";
        }

        int doctorId;

        try {
            doctorId = RecipeService.getInstance().findById(recipeId).getDoctorId();
            RecipeProlongationRequestService.getInstance().createEntity(0, recipeId, doctorId, "PENDING");
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            return () -> url + "&error=" + e.getMessage().replace(" ", "+");
        }
        return () -> url + "&message=Request+created!";
    }
}
