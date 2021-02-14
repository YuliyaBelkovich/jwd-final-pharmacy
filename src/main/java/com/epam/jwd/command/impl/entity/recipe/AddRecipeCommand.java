package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.entity.impl.RecipeService;

import java.time.LocalDate;

/**
 * Command responsible for adding new {@link com.epam.jwd.domain.Recipe}
 * Returns error message if mandatory field is missing or if the error occur on the inner levels
 */
public class AddRecipeCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int patientId;
        int doctorId;
        int medicineId;
        double dose;
        int duration;
        String url = (String) requestContext.getSession().getAttribute("previousPage");
        if (requestContext.hasParameter("recipe_patient") && requestContext.hasParameter("recipe_doctor")
                && requestContext.hasParameter("recipe_medicine") && requestContext.hasParameter("recipe_dose")
                && requestContext.hasParameter("recipe_duration")) {
            patientId = Integer.parseInt(requestContext.getParameter("recipe_patient"));
            doctorId = Integer.parseInt(requestContext.getParameter("recipe_doctor"));
            medicineId = Integer.parseInt(requestContext.getParameter("recipe_medicine"));
            dose = Double.parseDouble(requestContext.getParameter("recipe_dose"));
            duration = Integer.parseInt(requestContext.getParameter("recipe_duration"));
        } else {
            return () -> url + "&error=Missing+mandatory+field";
        }
        try {
            RecipeService.getInstance().createEntity(0, patientId, medicineId, dose, duration, doctorId, LocalDate.now());
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            return () -> url + "&error=" + e.getMessage().replace(" ", "+");
        }
        return () -> url + "&message=Recipe+created!";

    }
}
