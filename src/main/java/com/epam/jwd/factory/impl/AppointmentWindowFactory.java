package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.WindowStatus;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.UnknownEntityException;
import com.epam.jwd.factory.EntityFactory;

import java.time.LocalDateTime;

public class AppointmentWindowFactory implements EntityFactory<AppointmentWindow> {

    private static AppointmentWindowFactory factory = new AppointmentWindowFactory();

    private AppointmentWindowFactory() {
    }

    public static AppointmentWindowFactory getInstance() {
        return factory;
    }

    @Override
    public AppointmentWindow create(Object... args) throws FactoryException {
        AppointmentWindow appointmentWindow;
        try {
            appointmentWindow = new AppointmentWindow((int) args[0],
                    (int) args[1],
                    (LocalDateTime) args[2],
                    WindowStatus.resolveStatusByDBName((String) args[3])
            );
        } catch (ClassCastException e) {
            throw new FactoryException("Wrong arguments during the creation of the AppointmentWindow object");
        }
        return appointmentWindow;
    }
}
