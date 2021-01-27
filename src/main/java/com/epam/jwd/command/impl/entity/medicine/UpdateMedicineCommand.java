package com.epam.jwd.command.impl.entity.medicine;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.Medicine;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.MedicineFactory;
import com.epam.jwd.service.impl.MedicineService;

public class UpdateMedicineCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("Message", "");
        requestContext.getSession().setAttribute("Error", "");

        int id;
        String nameMedicine;
        boolean recipe_requirement = false;
        String information;
        double dose;
        double price;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("medicine_id") && requestContext.hasParameter("medicine_name") && requestContext.hasParameter("medicine_dose")
                && requestContext.hasParameter("medicine_price") && requestContext.hasParameter("medicine_info")) {
            if (requestContext.hasParameter("recipe_requirement") && requestContext.getParameter("recipe_requirement").equals("on")) {
                recipe_requirement = true;
            }
            nameMedicine = requestContext.getParameter("medicine_name");
            id = Integer.parseInt(requestContext.getParameter("medicine_id"));
            information = requestContext.getParameter("medicine_info");
            dose = Double.parseDouble(requestContext.getParameter("medicine_dose"));
            price = Double.parseDouble(requestContext.getParameter("medicine_price"));
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return () -> url;
        }

        Medicine medicine;
        try {
            medicine = MedicineFactory.getInstance().create(id, nameMedicine, dose, recipe_requirement, information, price);
            MedicineService.getInstance().update(medicine);
        } catch (FactoryException | DAOException | EntityNotFoundException | ValidationException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            return () -> url;
        }

        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Medicine updated!\n" + medicine.toString());
        requestContext.getSession().setAttribute("previousPage", requestContext.getUrl());
        return () -> url;
    }
}
