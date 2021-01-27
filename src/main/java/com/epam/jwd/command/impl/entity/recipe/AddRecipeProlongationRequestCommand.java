package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.RecipeProlongationRequestService;
import com.epam.jwd.service.impl.RecipeService;

public class AddRecipeProlongationRequestCommand implements Command {
  //  private static final ResponseContext ADD_REQUEST_RESULT = () -> "/pharmacy?command=go_to_recipe_page";

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int recipeId;
        String url = (String) requestContext.getSession().getAttribute("previousPage");
        if (requestContext.hasParameter("request_recipe")) {
            recipeId = Integer.parseInt(requestContext.getParameter("request_recipe"));
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return () -> url;
        }
        int doctorId;
        RecipeProlongationRequest request;
        try {
            doctorId = RecipeService.getInstance().findById(recipeId).getDoctorId();
            request = RecipeProlongationRequestService.getInstance().createEntity(0, recipeId, doctorId, "PENDING");
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            return () -> url;
        }
        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Recipe prolongation request is sent.\n");
        return () -> url;
    }
}
