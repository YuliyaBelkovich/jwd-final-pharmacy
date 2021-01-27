package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.PageName;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.RecipeProlongationRequestCriteria;
import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.domain.RecipeRequestStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.UnknownEntityException;
import com.epam.jwd.service.impl.RecipeProlongationRequestService;

import java.util.List;

public class SearchRecipeProlongationRequestCommand implements Command {
    private static final ResponseContext REQUEST_NOT_FOUND = PageName.SEARCH_RECIPE_REQUEST::getJspFileName;
    private static final ResponseContext SEARCH_RESULT = PageName.SEARCH_RECIPE_REQUEST_RESULT::getJspFileName;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        int recipeId = 0;
        int doctorId = 0;
        RecipeRequestStatus status = null;
        if (requestContext.hasParameter("request_status")) {
            try {
                status = RecipeRequestStatus.resolveStatusByDBName(requestContext.getParameter("request_status"));
            } catch (UnknownEntityException e) {
                requestContext.setAttribute("Error", "Status should be PENDING CONFIRMED or REJECTED");
                return REQUEST_NOT_FOUND;
            }
        }
        if (requestContext.hasParameter("request_id")) {
            id = Integer.parseInt(requestContext.getParameter("request_id"));
        }
        if (requestContext.hasParameter("request_recipe")) {
            recipeId = Integer.parseInt(requestContext.getParameter("request_recipe"));
        }
        if (requestContext.hasParameter("request_doctor")) {
            doctorId = Integer.parseInt(requestContext.getParameter("request_doctor"));
        }
        Criteria<RecipeProlongationRequest> criteria = RecipeProlongationRequestCriteria.builder()
                .id(id)
                .setDoctorId(doctorId)
                .setRecipeId(recipeId)
                .setStatus(status)
                .build();
        List<RecipeProlongationRequest> requests;
        try {
            requests = RecipeProlongationRequestService.getInstance().findByCriteria(criteria);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Error", "Request not found");
            return REQUEST_NOT_FOUND;
        }
        requestContext.setAttribute("Request", requests);
        return SEARCH_RESULT;
    }
}
