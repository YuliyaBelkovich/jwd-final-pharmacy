package com.epam.jwd.command.impl.entity.appointment;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.AppointmentWindowFactory;
import com.epam.jwd.service.entity.impl.AppointmentWindowService;

import java.time.LocalDateTime;

public class UpdateAppointmentWindowCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("Message", "");
        requestContext.getSession().setAttribute("Error", "");

        int id;
        int doctorId;
        LocalDateTime dateTime;
        String status;
        if(requestContext.hasParameter("window_id")&&requestContext.hasParameter("window_doctor")&&requestContext.hasParameter("window_date")&&
                requestContext.hasParameter("window_status")){
            id = Integer.parseInt(requestContext.getParameter("window_id"));
            doctorId=Integer.parseInt(requestContext.getParameter("window_doctor"));
            dateTime=LocalDateTime.parse(requestContext.getParameter("window_date"));
            status = requestContext.getParameter("window_status");
        }else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            requestContext.getSession().setAttribute("showHiddenScope", true);
            return () -> "/pharmacy?command=go_to_appointment_window_page&window_id=" + requestContext.getParameter("window_id");
        }
        AppointmentWindow window;
        try{
            window = AppointmentWindowFactory.getInstance().create(id,doctorId,dateTime,status);
            AppointmentWindowService.getInstance().update(window);
        } catch (FactoryException | DAOException | EntityNotFoundException | ValidationException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            requestContext.getSession().setAttribute("showHiddenScope", true);
            int finalId = id;
            return () -> "/pharmacy?command=go_to_appointment_window_page&window_id=" + finalId;
        }
        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Appointment edited !\n" + window.toString());
        AppointmentWindow finalWindow = window;
        requestContext.getSession().setAttribute("showHiddenScope",false);
        return () -> "/pharmacy?command=go_to_appointment_window_page&window_id=" + finalWindow.getId();
    }
}
