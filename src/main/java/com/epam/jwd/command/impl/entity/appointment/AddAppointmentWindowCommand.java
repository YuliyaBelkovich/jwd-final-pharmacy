package com.epam.jwd.command.impl.entity.appointment;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.AppointmentWindowService;

import java.time.LocalDateTime;

public class AddAppointmentWindowCommand implements Command {
    private static final ResponseContext ADD_WINDOW_RESULT = () -> "/pharmacy?command=go_to_add_appointment_window_page";

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int doctorId;
        LocalDateTime dateTime;

        if (requestContext.hasParameter("window_doctor") && requestContext.hasParameter("window_date")) {
            doctorId = Integer.parseInt(requestContext.getParameter("window_doctor"));
            dateTime = LocalDateTime.parse(requestContext.getParameter("window_date"));
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return ADD_WINDOW_RESULT;
        }

        try {
            AppointmentWindowService.getInstance().createEntity(0, doctorId, dateTime, "FREE");
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.setAttribute("Error", e.getMessage());
            return ADD_WINDOW_RESULT;
        }

        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Appointment window created!\n");
        return ADD_WINDOW_RESULT;
    }
}
