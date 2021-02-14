package com.epam.jwd.dao.impl;

import com.epam.jwd.domain.*;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.impl.*;
import com.epam.jwd.pool.ConnectionPool;
import com.epam.jwd.pool.ConnectionProxy;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.testng.Assert.*;

public class AbstractDaoTest {

    String propertiesFileName = "src/test/resources/application_test.properties";

    @BeforeTest
    public void init() {
        ApplicationProperties.setPropertiesFileName(propertiesFileName);
        ApplicationProperties properties = ApplicationProperties.getInstance();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void testAppointmentCrud() throws FactoryException, EntityNotFoundException, DAOException {
        Appointment expected = AppointmentFactory.getInstance().create(1, 1, 2, LocalDateTime.of(2021, 2, 15, 19, 0, 0), "test appointment", "CANCELLED");
        Appointment actual = AppointmentDao.getInstance().findEntityById(1);

        assertEquals(actual, expected);

        expected = AppointmentFactory.getInstance().create(2, 1, 2, LocalDateTime.of(2021, 2, 15, 20, 0, 0), "test appointment", "CANCELLED");
        AppointmentDao.getInstance().add(expected);
        actual = AppointmentDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        expected = AppointmentFactory.getInstance().create(actual.getId(), 1, 2, LocalDateTime.of(2021, 2, 16, 20, 0, 0), "test appointment", "PLANNED");
        AppointmentDao.getInstance().update(expected);
        actual = AppointmentDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        AppointmentDao.getInstance().delete(actual);
        AppointmentDao.getInstance().findEntityById(actual.getId());
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void testAppointmentWindowCrud() throws FactoryException, EntityNotFoundException, DAOException {
        AppointmentWindow expected = AppointmentWindowFactory.getInstance().create(1, 2, LocalDateTime.of(2022, 2, 8, 20, 0, 0), "FREE");
        AppointmentWindow actual = AppointmentWindowDao.getInstance().findEntityById(1);

        assertEquals(actual, expected);

        expected = AppointmentWindowFactory.getInstance().create(0, 2, LocalDateTime.of(2021, 2, 16, 19, 0, 0), "FREE");
        AppointmentWindowDao.getInstance().add(expected);
        actual = AppointmentWindowDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        expected = AppointmentWindowFactory.getInstance().create(actual.getId(), 2, LocalDateTime.of(2021, 2, 17, 19, 0, 0), "FREE");
        AppointmentWindowDao.getInstance().update(expected);
        actual = AppointmentWindowDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        AppointmentWindowDao.getInstance().delete(actual);
        AppointmentWindowDao.getInstance().findEntityById(actual.getId());
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void testBankCrud() throws FactoryException, EntityNotFoundException, DAOException {
        BankAccount expected = BankAccountFactory.getInstance().create(1, 1, "1111222233334444", "TEST PATIENT", "10/23", 111);
        BankAccount actual = BankAccountDao.getInstance().findEntityById(1);

        assertEquals(actual, expected);

        expected = BankAccountFactory.getInstance().create(0, 1, "1111222233335555", "TEST PATIENT", "10/23", 111);
        BankAccountDao.getInstance().add(expected);
        actual = BankAccountDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        expected = BankAccountFactory.getInstance().create(actual.getId(), 1, "1111222244445555", "TEST PATIENT", "10/23", 111);
        BankAccountDao.getInstance().update(expected);
        actual = BankAccountDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        BankAccountDao.getInstance().delete(actual);
        BankAccountDao.getInstance().findEntityById(actual.getId());
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void testMedicineCrud() throws FactoryException, EntityNotFoundException, DAOException {
        Medicine expected = MedicineFactory.getInstance().create(1, "Test Medicine", 0.1, true, "test info", 1.0);
        Medicine actual = MedicineDao.getInstance().findEntityById(1);

        assertEquals(actual, expected);

        expected = MedicineFactory.getInstance().create(0, "Test Medicine", 0.2, true, "test info", 1.0);
        MedicineDao.getInstance().add(expected);
        actual = MedicineDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        expected = MedicineFactory.getInstance().create(actual.getId(), "Test Medicine", 0.3, true, "test info", 1.0);
        MedicineDao.getInstance().update(expected);
        actual = MedicineDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        MedicineDao.getInstance().delete(actual);
        MedicineDao.getInstance().findEntityById(actual.getId());
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void testPaymentCrud() throws FactoryException, EntityNotFoundException, DAOException {
        Payment expected = PaymentFactory.getInstance().create(1, 1.0, "1111222233334444", LocalDateTime.of(2021, 2, 8, 19, 0, 0));
        Payment actual = PaymentDao.getInstance().findEntityById(1);

        assertEquals(actual, expected);

        expected = PaymentFactory.getInstance().create(1, 1.0, "1111222233335555", LocalDateTime.of(2021, 2, 8, 19, 0, 0));
        PaymentDao.getInstance().add(expected);
        actual = PaymentDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        expected = PaymentFactory.getInstance().create(actual.getId(), 1.0, "1111222233336666", LocalDateTime.of(2021, 2, 8, 19, 0, 0));
        PaymentDao.getInstance().update(expected);
        actual = PaymentDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        PaymentDao.getInstance().delete(actual);
        PaymentDao.getInstance().findEntityById(actual.getId());
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void testRecipeCrud() throws FactoryException, EntityNotFoundException, DAOException {
        Recipe expected = RecipeFactory.getInstance().create(1, 1, 1, 10.0, 30, 2, LocalDate.of(2021, 2, 6));
        Recipe actual = RecipeDao.getInstance().findEntityById(1);

        assertEquals(actual, expected);

        expected = RecipeFactory.getInstance().create(1, 1, 1, 10.0, 31, 2, LocalDate.of(2021, 2, 6));
        RecipeDao.getInstance().add(expected);
        actual = RecipeDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        expected = RecipeFactory.getInstance().create(actual.getId(), 1, 1, 10.0, 32, 2, LocalDate.of(2021, 2, 6));
        RecipeDao.getInstance().update(expected);
        actual = RecipeDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        RecipeDao.getInstance().delete(actual);
        RecipeDao.getInstance().findEntityById(actual.getId());
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void testRecipeProlongationCrud() throws FactoryException, EntityNotFoundException, DAOException {
        RecipeProlongationRequest expected = RecipeProlongationRequestFactory.getInstance().create(1, 1, 2, "PENDING");
        RecipeProlongationRequest actual = RecipeProlongationRequestDao.getInstance().findEntityById(1);

        assertEquals(actual, expected);

        expected = RecipeProlongationRequestFactory.getInstance().create(1, 1, 2, "CONFIRMED");
        RecipeProlongationRequestDao.getInstance().add(expected);
        actual = RecipeProlongationRequestDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        expected = RecipeProlongationRequestFactory.getInstance().create(actual.getId(), 1, 2, "REJECTED");
        RecipeProlongationRequestDao.getInstance().update(expected);
        actual = RecipeProlongationRequestDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        RecipeProlongationRequestDao.getInstance().delete(actual);
        RecipeProlongationRequestDao.getInstance().findEntityById(actual.getId());
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void testUserCrud() throws FactoryException, EntityNotFoundException, DAOException {
        User expected = UserFactory.getInstance().create(1, "Test Patient", "test_patient@email.com", "12345", "PATIENT", "ACTIVE");
        User actual = UserDao.getInstance().findEntityById(1);

        assertEquals(actual, expected);

        expected = UserFactory.getInstance().create(1, "Test Patient Add", "test_patient@email.com", "12345", "PATIENT", "ACTIVE");
        UserDao.getInstance().add(expected);
        actual = UserDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        expected = UserFactory.getInstance().create(actual.getId(), "Test Patient Update", "test_patient@email.com", "12345", "PATIENT", "ACTIVE");
        UserDao.getInstance().update(expected);
        actual = UserDao.getInstance().findEntityById(expected.getId());

        assertEquals(actual, expected);

        UserDao.getInstance().delete(actual);
        UserDao.getInstance().findEntityById(actual.getId());
    }
}