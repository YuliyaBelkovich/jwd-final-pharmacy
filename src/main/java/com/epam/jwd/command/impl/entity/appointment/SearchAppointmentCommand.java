package com.epam.jwd.command.impl.entity.appointment;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.PageName;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.criteria.AppointmentCriteria;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.AppointmentStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.UnknownEntityException;
import com.epam.jwd.service.impl.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;

public class SearchAppointmentCommand implements Command {

    private static final ResponseContext SEARCH_RESULT = PageName.SEARCH_APPOINTMENT_PAGE::getJspFileName;

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        int patientId = 0;
        int doctorId = 0;
        LocalDateTime dateTime = null;
        String status = null;
        if (requestContext.hasParameter("appointment_id")) {
            id = Integer.parseInt(requestContext.getParameter("appointment_id"));
        }
        if (requestContext.hasParameter("appointment_patient")) {
            patientId = Integer.parseInt(requestContext.getParameter("appointment_patient"));
        }
        if (requestContext.hasParameter("appointment_doctor")) {
            doctorId = Integer.parseInt(requestContext.getParameter("appointment_doctor"));
        }
        if (requestContext.hasParameter("appointment_date")) {
            dateTime = LocalDateTime.parse(requestContext.getParameter("appointment_date"));
        }
        if (requestContext.hasParameter("appointment_status")) {
            status = requestContext.getParameter("appointment_status");
        }
        Criteria<Appointment> criteria = AppointmentCriteria.builder()
                    .id(id)
                    .setPatientId(patientId)
                    .setDoctorId(doctorId)
                    .setDateTime(dateTime)
                    .setAppointmentStatus(AppointmentStatus.resolveStatusByDBName(status))
                    .build();
        List<Appointment> appointments;
        try {
            appointments = AppointmentService.getInstance().findByCriteria(criteria);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Error", "Appointment not found");
            return SEARCH_RESULT;
        }
        requestContext.setAttribute("Appointment", appointments);
        requestContext.getSession().setAttribute("previousPage", requestContext.getUrl());
        return SEARCH_RESULT;
    }
}
