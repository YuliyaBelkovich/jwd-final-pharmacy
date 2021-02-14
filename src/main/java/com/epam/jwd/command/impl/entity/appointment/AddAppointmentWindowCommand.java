package com.epam.jwd.command.impl.entity.appointment;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.entity.impl.AppointmentWindowService;

import java.time.LocalDateTime;

public class AddAppointmentWindowCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int doctorId;
        LocalDateTime dateTime;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("window_doctor") && requestContext.hasParameter("window_date")) {
            doctorId = Integer.parseInt(requestContext.getParameter("window_doctor"));
            dateTime = LocalDateTime.parse(requestContext.getParameter("window_date"));
        } else {
            return () -> url+"&error=Missing+mandatory+field";
        }

        try {
            AppointmentWindowService.getInstance().createEntity(0, doctorId, dateTime, "FREE");
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            return () -> url+"&"+e.getMessage().replace(" ","+");
        }

        return () -> url+"&message=Timeslot+created!";
    }
}
