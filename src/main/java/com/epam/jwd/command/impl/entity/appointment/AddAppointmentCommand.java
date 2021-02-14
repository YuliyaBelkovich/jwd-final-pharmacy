package com.epam.jwd.command.impl.entity.appointment;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.criteria.AppointmentWindowCriteria;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.User;
import com.epam.jwd.domain.WindowStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.entity.impl.AppointmentService;
import com.epam.jwd.service.entity.impl.AppointmentWindowService;
import com.epam.jwd.service.entity.impl.UserService;
import com.epam.jwd.service.mail.MailService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddAppointmentCommand implements Command {

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
            return () -> url+"&error=Missing+mandatory+field";
        }


        try {
            AppointmentService.getInstance().createEntity(0, patientId, doctorId, dateTime, null, "PLANNED");
        } catch (FactoryException | DAOException | ValidationException | EntityNotFoundException e) {
            return () -> url+"&"+e.getMessage().replace(" ","+");
        }

        Criteria<AppointmentWindow> criteria = AppointmentWindowCriteria.builder().setDoctorId(doctorId).setDateTime(dateTime).build();
        AppointmentWindow window;
        User doctor;
        User patient;

        try {
            window = AppointmentWindowService.getInstance().findByCriteria(criteria).get(0);
            window.setStatus(WindowStatus.BUSY);
            AppointmentWindowService.getInstance().update(window);
            patient = UserService.getInstance().findById(patientId);
            doctor = UserService.getInstance().findById(doctorId);
        } catch (DAOException | ValidationException | EntityNotFoundException e) {
            return () -> url+"&"+e.getMessage().replace(" ","+");
        }
        MailService.getInstance().sendTextEmail(doctor.getEmail(), "New Appointment", "Patient " + patient.getName() + " is signed up for an appointment " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        MailService.getInstance().sendTextEmail(patient.getEmail(), "New Appointment", "You've just signed up for an appointment to dr." + doctor.getName() + ". In case of cancellation please contact doctor. Email:  " + doctor.getEmail() + ". Your appointment will take place at " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return () -> url+"&message=Appointment+created!";
    }
}
