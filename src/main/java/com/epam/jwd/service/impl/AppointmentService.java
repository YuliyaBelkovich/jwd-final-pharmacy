package com.epam.jwd.service.impl;

import com.epam.jwd.criteria.AppointmentCriteria;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.impl.AppointmentDao;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.domain.AppointmentStatus;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.factory.impl.AppointmentFactory;
import com.epam.jwd.service.EntityService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class AppointmentService implements EntityService<Appointment> {

    private static AppointmentService appointmentService;

    private AppointmentService(){}

    public static AppointmentService getInstance() {
        if (appointmentService == null) {
            return appointmentService = new AppointmentService();
        } else return appointmentService;
    }

    @Override
    public EntityDao<Appointment> getEntityDao() {
        EntityDao<Appointment> dao = AppointmentDao.getInstance();
        return dao;
    }

    @Override
    public CopyOnWriteArrayList<Appointment> getEntityCache() {
        return getApplicationContext().getCache(Appointment.class);
    }

    @Override
    public EntityFactory<Appointment> getEntityFactory() {
        return AppointmentFactory.getInstance();
    }

    @Override
    public List<Appointment> findInCashByCriteria(Criteria<Appointment> criteria) {
        AppointmentCriteria appointmentCriteria = (AppointmentCriteria) criteria;
        return getEntityCache()
                .stream()
                .filter(appointment -> {
                    int id = appointmentCriteria.getId();
                    return id == 0 || id == appointment.getId();
                })
                .filter(appointment -> {
                    int id = appointmentCriteria.getDoctorId();
                    return id == 0 || id == appointment.getDoctorId();
                })
                .filter(appointment -> {
                    int id = appointmentCriteria.getPatientId();
                    return id == 0 || id == appointment.getPatientId();
                })
                .filter(appointment -> {
                    LocalDateTime time = appointmentCriteria.getDateTime();
                    return time == null || appointment.getDateTime().equals(time);
                })
                .filter(appointment -> {
                    AppointmentStatus status = appointmentCriteria.getAppointmentStatus();
                    return status == null || appointment.getAppointmentStatus().equals(status);
                })
                .collect(Collectors.toList());
    }



}
