package com.epam.jwd.service.entity.impl;

import com.epam.jwd.criteria.AppointmentWindowCriteria;
import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.dao.impl.AppointmentWindowDao;
import com.epam.jwd.domain.AppointmentWindow;
import com.epam.jwd.domain.WindowStatus;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.factory.impl.AppointmentWindowFactory;
import com.epam.jwd.service.entity.EntityService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class AppointmentWindowService implements EntityService<AppointmentWindow> {

    private static AppointmentWindowService service = new AppointmentWindowService();

    private AppointmentWindowService(){}

    public static AppointmentWindowService getInstance() {
        return service;
    }
    @Override
    public EntityDao<AppointmentWindow> getEntityDao() {
        EntityDao<AppointmentWindow> dao = AppointmentWindowDao.getInstance();
        return dao;
    }

    @Override
    public CopyOnWriteArrayList<AppointmentWindow> getEntityCache() {
        return getApplicationContext().getCache(AppointmentWindow.class);
    }

    @Override
    public EntityFactory<AppointmentWindow> getEntityFactory() {
        return AppointmentWindowFactory.getInstance();
    }

    @Override
    public List<AppointmentWindow> findInCashByCriteria(Criteria<AppointmentWindow> criteria) {

        AppointmentWindowCriteria windowCriteria = (AppointmentWindowCriteria) criteria;
        return getEntityCache()
                .stream()
                .filter(window -> {
                    int id = windowCriteria.getId();
                    return id == 0 || id == window.getId();
                })
                .filter(window -> {
                    int id = windowCriteria.getDoctorId();
                    return id == 0 || id == window.getDoctorId();
                })
                .filter(window-> {
                    LocalDateTime time = windowCriteria.getDateTime();
                    return time == null || window.getDateTime().equals(time);
                })
                .filter(window -> {
                    WindowStatus status = windowCriteria.getStatus();
                    return status == null || window.getStatus().equals(status);
                })
                .collect(Collectors.toList());
    }
}
