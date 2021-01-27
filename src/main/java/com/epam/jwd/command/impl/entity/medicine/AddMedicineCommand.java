package com.epam.jwd.command.impl.entity.medicine;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.MedicineService;

public class AddMedicineCommand implements Command {

    private static final ResponseContext ADD_MEDICINE_RESULT = () -> "/pharmacy?command=go_to_add_medicine_page";

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        String nameMedicine;
        boolean recipe_requirement = false;
        String information;
        double dose;
        double price;

        if (requestContext.hasParameter("medicine_name") && requestContext.hasParameter("medicine_dose")
                && requestContext.hasParameter("medicine_price") && requestContext.hasParameter("medicine_info")) {
            nameMedicine = requestContext.getParameter("medicine_name");
            if (requestContext.hasParameter("recipe_requirement") && requestContext.getParameter("recipe_requirement").equals("on")) {
                recipe_requirement = true;
            }
            information = requestContext.getParameter("medicine_info");
            dose = Double.parseDouble(requestContext.getParameter("medicine_dose"));
            price = Double.parseDouble(requestContext.getParameter("medicine_price"));
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return ADD_MEDICINE_RESULT;
        }

        try {
            MedicineService.getInstance().createEntity(0, nameMedicine, dose, recipe_requirement, information, price);
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.setAttribute("Error", e.getMessage());
            return ADD_MEDICINE_RESULT;
        }

        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Medicine created!\n");
        return ADD_MEDICINE_RESULT;
    }
}
