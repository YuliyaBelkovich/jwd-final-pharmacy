package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.RecipeProlongationRequestCriteria;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.domain.RecipeRequestStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.entity.impl.RecipeProlongationRequestService;

import java.util.List;
/**
 * Class responsible for searching {@link RecipeProlongationRequest} objects according to given criteria
 * Returns error message when request not found
 */
public class SearchRecipeProlongationRequestCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        int recipeId = 0;
        int doctorId = 0;
        RecipeRequestStatus status = null;
        String url = (String) requestContext.getSession().getAttribute("previousPage");
        if (requestContext.hasParameter("request_status")) {
//            try {
                status = RecipeRequestStatus.resolveStatusByDBName(requestContext.getParameter("request_status"));
//            } catch (UnknownEntityException e) {
//                requestContext.setAttribute("Error", "Status should be PENDING CONFIRMED or REJECTED");
//                return () -> url;
//            }
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
            return () -> url;
        }
        requestContext.setAttribute("Request", requests);
        return () -> url;
    }
}
