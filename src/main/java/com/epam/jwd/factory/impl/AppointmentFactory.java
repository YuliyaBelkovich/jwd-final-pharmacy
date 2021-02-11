package com.epam.jwd.factory.impl;

import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.AppointmentStatus;
import com.epam.jwd.exception.FactoryException;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;


public class AppointmentFactory implements EntityFactory<Appointment> {

    private static AppointmentFactory appointmentFactory = new AppointmentFactory();
    private static final Logger logger = Logger.getLogger(AppointmentFactory.class);

    private AppointmentFactory(){}

    public static AppointmentFactory getInstance(){
        return appointmentFactory;
    }
    @Override
    public Appointment create(Object... args) throws FactoryException {
        Appointment appointment;
        try {
            appointment = new Appointment((int) args[0],
                    (int) args[1],
                    (int) args[2],
                    (LocalDateTime) args[3],
                    (String) args[4],
                    AppointmentStatus.resolveStatusByDBName((String) args[5]));
        } catch (ClassCastException e){
            logger.error(e.getMessage());
            throw new FactoryException("Wrong arguments during the creation of the Appointment object");
        }
        return appointment;
    }
}
