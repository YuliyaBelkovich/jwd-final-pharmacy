package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.Recipe;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.RecipeService;

import java.time.LocalDate;
import java.util.Date;

public class AddRecipeCommand implements Command {
    private static final ResponseContext ADD_RECIPE_RESULT = () -> "/pharmacy?command=go_to_add_recipe_page";

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
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return () -> url;
        }
        try {
            RecipeService.getInstance().createEntity(0, patientId, medicineId, dose, duration, doctorId, LocalDate.now());
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.setAttribute("Error", e.getMessage() + "error");
            return () -> url;
        }
        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Recipe created!\n");
        return () -> url;

    }
}
