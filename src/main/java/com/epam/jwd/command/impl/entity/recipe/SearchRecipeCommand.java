package com.epam.jwd.command.impl.entity.recipe;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.PageName;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.RecipeCriteria;
import com.epam.jwd.domain.Recipe;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.impl.RecipeService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class SearchRecipeCommand implements Command {
    private static final ResponseContext SEARCH_RESULT = PageName.SEARCH_RECIPE_PAGE::getJspFileName;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        int patientId = 0;
        int doctorId = 0;
        int medicineId = 0;
        double dose = 0;
        LocalDate date = null;
        if (requestContext.hasParameter("recipe_id")) {
            id = Integer.parseInt(requestContext.getParameter("recipe_id"));
        }
        if (requestContext.hasParameter("recipe_patient")) {
            patientId = Integer.parseInt(requestContext.getParameter("recipe_patient"));
        }
        if (requestContext.hasParameter("recipe_doctor")) {
            doctorId = Integer.parseInt(requestContext.getParameter("recipe_doctor"));
        }
        if (requestContext.hasParameter("recipe_medicine")) {
            medicineId = Integer.parseInt(requestContext.getParameter("recipe_medicine"));
        }
        if (requestContext.hasParameter("recipe_dose")) {
            dose = Double.parseDouble(requestContext.getParameter("recipe_dose"));
        }
        if (requestContext.hasParameter("recipe_date")) {
            date = LocalDate.parse(requestContext.getParameter("recipe_date"));
        }

        Criteria<Recipe> criteria = RecipeCriteria.builder()
                .id(id)
                .setPatientId(patientId)
                .setDoctorId(doctorId)
                .setMedicineId(medicineId)
                .setDose(dose)
                .setDate(date)
                .build();

        List<Recipe> recipes;

        try {
            recipes = RecipeService.getInstance().findByCriteria(criteria);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Error", "Recipe not found");
            return SEARCH_RESULT;
        }
        requestContext.setAttribute("Recipe", recipes);
        requestContext.getSession().setAttribute("previousPage", requestContext.getUrl());
        return SEARCH_RESULT;
    }
}
