package com.epam.jwd.command.impl.entity.appointment;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.criteria.AppointmentWindowCriteria;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.WindowStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.impl.AppointmentService;
import com.epam.jwd.service.impl.AppointmentWindowService;

import java.time.LocalDateTime;

public class AddAppointmentCommand implements Command {
  //  private static final ResponseContext ADD_APPOINTMENT_RESULT = () -> "/pharmacy?command=go_to_doctor_page";

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        int patientId;
        int doctorId;
        LocalDateTime dateTime;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("appointment_patient") && requestContext.hasParameter("appointment_doctor")
                && requestContext.hasParameter("appointment_date")) {
            patientId = Integer.parseInt(requestContext.getParameter("appointment_patient"));
            doctorId = Integer.parseInt(requestContext.getParameter("appointment_doctor"));
            dateTime = LocalDateTime.parse(requestContext.getParameter("appointment_date"));
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return () -> url;
        }


        try {
            AppointmentService.getInstance().createEntity(0, patientId, doctorId, dateTime, null, "PLANNED");
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            return () -> url;
        }

        Criteria<AppointmentWindow> criteria = AppointmentWindowCriteria.builder().setDoctorId(doctorId).setDateTime(dateTime).build();
        AppointmentWindow window;

        try {
            window = AppointmentWindowService.getInstance().findByCriteria(criteria).get(0);
            window.setStatus(WindowStatus.BUSY);
            AppointmentWindowService.getInstance().update(window);
        } catch (DAOException | ValidationException | EntityNotFoundException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            return () -> url;
        }
//todo mail
        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "Appointment created!\n");
        return () -> url;
    }
}
