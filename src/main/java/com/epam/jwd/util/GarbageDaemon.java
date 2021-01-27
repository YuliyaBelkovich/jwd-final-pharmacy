package com.epam.jwd.util;

import com.epam.jwd.context.ApplicationContext;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.WindowStatus;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.service.impl.AppointmentWindowService;

import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;

public class GarbageDaemon extends Thread {

    ApplicationContext context;

    public GarbageDaemon(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        while(true){
            collectAppointmentWindow();
            try {
                sleep(1800000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void collectAppointmentWindow() {
        CopyOnWriteArrayList<AppointmentWindow> cache = context.getCache(AppointmentWindow.class);
        for (AppointmentWindow appointmentWindow : cache) {
            if (appointmentWindow.getStatus().equals(WindowStatus.BUSY) || appointmentWindow.getDateTime().isBefore(LocalDateTime.now().minusMinutes(30))) {
                try {
                    AppointmentWindowService.getInstance().delete(appointmentWindow);
                } catch (DAOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
