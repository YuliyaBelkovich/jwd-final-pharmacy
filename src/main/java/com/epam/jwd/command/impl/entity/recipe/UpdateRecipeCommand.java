package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.Recipe;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.RecipeFactory;
import com.epam.jwd.service.entity.impl.RecipeService;

import java.time.LocalDate;
/**
 * Class responsible for updating the {@link Recipe} object
 * Requires all fields to update
 */
public class UpdateRecipeCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("Message", "");
        requestContext.getSession().setAttribute("Error", "");

        int id;
        int patientId;
        int doctorId;
        int medicineId;
        double dose;
        int duration;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("recipe_id") && requestContext.hasParameter("recipe_patient") && requestContext.hasParameter("recipe_doctor")
                && requestContext.hasParameter("recipe_medicine") && requestContext.hasParameter("recipe_dose")
                && requestContext.hasParameter("recipe_duration")) {
            id = Integer.parseInt(requestContext.getParameter("recipe_id"));
            patientId = Integer.parseInt(requestContext.getParameter("recipe_patient"));
            doctorId = Integer.parseInt(requestContext.getParameter("recipe_doctor"));
            medicineId = Integer.parseInt(requestContext.getParameter("recipe_medicine"));
            dose = Double.parseDouble(requestContext.getParameter("recipe_dose"));
            duration = Integer.parseInt(requestContext.getParameter("recipe_duration"));
        } else {
            return () -> url + "&error=Missing+mandatory+field";
        }
        Recipe recipe;
        try {
            recipe = RecipeFactory.getInstance().create(id, patientId, medicineId, dose, duration, doctorId, LocalDate.now());
            RecipeService.getInstance().update(recipe);
        } catch (FactoryException | DAOException | EntityNotFoundException | ValidationException e) {
            return () -> url + "&error=" + e.getMessage().replace(" ", "+");
        }
        return () -> url + "&message=Recipe+updated!";
    }
}
