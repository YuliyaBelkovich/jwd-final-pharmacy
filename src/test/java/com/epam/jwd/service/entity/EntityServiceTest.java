package com.epam.jwd.service.entity;

import com.epam.jwd.context.PharmacyCache;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.dao.impl.AppointmentDao;
import com.epam.jwd.domain.ApplicationProperties;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.AppointmentFactory;
import com.epam.jwd.pool.ConnectionPool;
import com.epam.jwd.service.entity.impl.AppointmentService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class EntityServiceTest {

    String propertiesFileName = "src/test/resources/application_test.properties";

    @BeforeTest
    public void init() throws DAOException {
        ApplicationProperties.setPropertiesFileName(propertiesFileName);
        ApplicationProperties properties = ApplicationProperties.getInstance();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        PharmacyCache.getInstance().initCache();
    }

    @Test
    public void testFindAllAppointment() throws FactoryException, DAOException {
        EntityDao<Appointment> dao = mock(AppointmentDao.class);
        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(AppointmentFactory.getInstance().create(1, 1, 2, LocalDateTime.of(2021, 2, 15, 19, 0, 0), "test appointment", "CANCELLED"));
        EntityService<Appointment> service = AppointmentService.getInstance();
        when(dao.getAll()).thenReturn(appointmentList);
        List<Appointment> actual = service.findAll();

        assertEquals(actual, appointmentList);
    }
}