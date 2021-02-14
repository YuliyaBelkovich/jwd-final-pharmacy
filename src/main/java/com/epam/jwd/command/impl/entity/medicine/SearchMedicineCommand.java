package com.epam.jwd.command.impl.entity.medicine;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.context.PageName;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.MedicineCriteria;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.Medicine;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.entity.impl.MedicineService;

import java.util.List;
/**
 * Class responsible for searching {@link Medicine} objects according to given criteria
 * Returns error message when medicine not found
 */
public class SearchMedicineCommand implements Command {

    private static final ResponseContext SEARCH_RESULT = PageName.SEARCH_MEDICINE_PAGE::getJspFileName;

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        String nameMedicine = null;
        boolean recipe_requirement = false;
        boolean non_recipe_requirement = false;
        double dose = 0;
        double price = 0;
        if (requestContext.hasParameter("medicine_name")) {
            nameMedicine = requestContext.getParameter("medicine_name");
        }
        if (requestContext.hasParameter("medicine_dose")) {
            dose = Double.parseDouble(requestContext.getParameter("medicine_dose"));
        }

        if (requestContext.hasParameter("medicine_price")) {
            price = Double.parseDouble(requestContext.getParameter("medicine_price"));
        }

        if (requestContext.hasParameter("recipe_requirement")) {
            if (requestContext.getParameter("recipe_requirement").equals("on")) {
                recipe_requirement = true;
            }
        }
        if (requestContext.hasParameter("non_recipe_requirement")) {
            if (requestContext.getParameter("non_recipe_requirement").equals("on")) {
                non_recipe_requirement = true;
            }
        }
        Criteria<Medicine> criteria = MedicineCriteria
                .builder()
                .setName(nameMedicine)
                .setRecipeRequirement(recipe_requirement)
                .setNonRecipeRequirement(non_recipe_requirement)
                .setDose(dose)
                .setPrice(price)
                .build();
        List<Medicine> medicineList;
        try {
            medicineList = MedicineService.getInstance().findByCriteria(criteria);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Error", "Medicine not found");
            return SEARCH_RESULT;
        }
        requestContext.setAttribute("Medicine", medicineList);
        requestContext.getSession().setAttribute("previousPage", requestContext.getUrl());
        return SEARCH_RESULT;
    }
}
