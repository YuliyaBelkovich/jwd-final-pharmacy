package com.epam.jwd.command.impl.entity.appointment;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.AppointmentFactory;
import com.epam.jwd.service.impl.AppointmentService;

import java.time.LocalDateTime;

public class UpdateAppointmentCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {

        int id;
        String info;
        String status;
        int patientId;
        int doctorId;
        LocalDateTime dateTime;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("appointment_id") && requestContext.hasParameter("appointment_info") &&
                requestContext.hasParameter("appointment_status") && requestContext.hasParameter("appointment_patient") && requestContext.hasParameter("appointment_doctor")
                && requestContext.hasParameter("appointment_date")) {

            id = Integer.parseInt(requestContext.getParameter("appointment_id"));
            info = requestContext.getParameter("appointment_info");
            status = requestContext.getParameter("appointment_status");
            patientId = Integer.parseInt(requestContext.getParameter("appointment_patient"));
            doctorId = Integer.parseInt(requestContext.getParameter("appointment_doctor"));
            dateTime = LocalDateTime.parse(requestContext.getParameter("appointment_date"));
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return () -> url;
        }

        Appointment appointment;
        try {
            appointment = AppointmentFactory.getInstance().create(id, patientId, doctorId, dateTime, info, status);
            AppointmentService.getInstance().update(appointment);
        } catch (FactoryException | EntityNotFoundException | DAOException | ValidationException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            return () -> url;
        }
        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Appointment edited !\n");
        requestContext.getSession().setAttribute("previousPage", requestContext.getUrl());
        return () -> url;
    }
}
