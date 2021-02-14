package com.epam.jwd.command.impl.entity.medicine;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.entity.impl.MedicineService;

public class AddMedicineCommand implements Command {

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
            return () -> "/pharmacy?command=go_to_user_main_page&error=Mising+mandatory+field";
        }

        try {
            MedicineService.getInstance().createEntity(0, nameMedicine, dose, recipe_requirement, information, price);
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            return () -> "/pharmacy?command=go_to_user_main_page&error="+e.getMessage().replace(" ","+");
        }

        requestContext.getSession().setAttribute("Message", "Medicine created!\n");
        return ()-> "/pharmacy?command=go_to_user_main_page&message=Medicine+created";
    }
}
