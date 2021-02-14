package com.epam.jwd.command.impl.entity.appointment;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.criteria.AppointmentWindowCriteria;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.WindowStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.entity.impl.AppointmentWindowService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class responsible for searching the {@link AppointmentWindow}
 * Returns error message when appointment window not found
 */
public class SearchAppointmentWindowCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = 0;
        int doctorId = 0;
        LocalDateTime dateTime = null;
        String status = null;
        String url = (String) requestContext.getSession().getAttribute("previousPage");
        if (requestContext.hasParameter("window_id")) {
            id = Integer.parseInt(requestContext.getParameter("window_id"));
        }
        if (requestContext.hasParameter("window_doctor")) {
            doctorId = Integer.parseInt(requestContext.getParameter("window_doctor"));
        }
        if (requestContext.hasParameter("window_date")) {
            dateTime = LocalDateTime.parse(requestContext.getParameter("window_date"));
        }
        if (requestContext.hasParameter("window_status")) {
            status = (requestContext.getParameter("window_status"));
        }

        Criteria<AppointmentWindow> criteria = AppointmentWindowCriteria.builder()
                .id(id)
                .setDoctorId(doctorId)
                .setDateTime(dateTime)
                .setStatus(WindowStatus.resolveStatusByDBName(status))
                .build();

        List<AppointmentWindow> windows;

        try {
            windows = AppointmentWindowService.getInstance().findByCriteria(criteria);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Error", "No timeslots found");
            return()-> url;
        }
        requestContext.getSession().setAttribute("previousPage",requestContext.getUrl());
        requestContext.setAttribute("Window", windows);
        return ()-> url;
    }
}
