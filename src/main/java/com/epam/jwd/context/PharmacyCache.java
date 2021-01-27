package com.epam.jwd.context;

import com.epam.jwd.dao.impl.*;
import com.epam.jwd.domain.*;
import com.epam.jwd.exception.DAOException;

import java.util.concurrent.CopyOnWriteArrayList;

public class PharmacyCache {

    private static PharmacyCache pharmacyCache = new PharmacyCache();

    private CopyOnWriteArrayList<Medicine> medicineCache = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Appointment> appointmentCache = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Order> orderCache = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Recipe> recipeCache = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<User> userCache = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<BankAccount> bankAccountCache = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Payment> paymentCache = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<RecipeProlongationRequest> requestCache = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<AppointmentWindow> windowCache = new CopyOnWriteArrayList<>();

    private PharmacyCache() {
    }

    public static PharmacyCache getInstance() {
        return pharmacyCache;
    }

    public CopyOnWriteArrayList getCache(String classname) {
        switch (classname) {
            case "com.epam.jwd.domain.Appointment": {
                return appointmentCache;
            }
            case "com.epam.jwd.domain.Medicine": {
                return medicineCache;
            }
            case "com.epam.jwd.domain.Order": {
                return orderCache;
            }
            case "com.epam.jwd.domain.Recipe": {
                return recipeCache;
            }
            case "com.epam.jwd.domain.User": {
                return userCache;
            }
            case "com.epam.jwd.domain.BankAccount": {
                return bankAccountCache;
            }
            case "com.epam.jwd.domain.Payment": {
                return paymentCache;
            }
            case "com.epam.jwd.domain.RecipeProlongationRequest": {
                return requestCache;
            }
            case "com.epam.jwd.domain.AppointmentWindow": {
                return windowCache;
            }
            default:
                return null;
        }
    }

    public void addToCache(Entity entity) {
        switch (entity.getClass().getName()) {
            case "com.epam.jwd.domain.Appointment": {
                appointmentCache.add((Appointment) entity);
                break;
            }
            case "com.epam.jwd.domain.Medicine": {
                medicineCache.add((Medicine) entity);
                break;
            }
            case "com.epam.jwd.domain.Order": {
                orderCache.add((Order) entity);
                break;
            }
            case "com.epam.jwd.domain.Recipe": {
                recipeCache.add((Recipe) entity);
                break;
            }
            case "com.epam.jwd.domain.User": {
                userCache.add((User) entity);
                break;
            }
            case "com.epam.jwd.domain.BankAccount": {
                bankAccountCache.add((BankAccount) entity);
                break;
            }
            case "com.epam.jwd.domain.Payment": {
                paymentCache.add((Payment) entity);
                break;
            }
            case "com.epam.jwd.domain.RecipeProlongationRequest": {
                requestCache.add((RecipeProlongationRequest) entity);
                break;
            }
            case "com.epam.jwd.domain.AppointmentWindow": {
                windowCache.add((AppointmentWindow) entity);
                break;
            }
            default: {
                break;
            }
        }
    }

    public void removeFromCache(Entity entity) {
        switch (entity.getClass().getName()) {
            case "com.epam.jwd.domain.Appointment": {
                appointmentCache.remove(entity);
                break;
            }
            case "com.epam.jwd.domain.Medicine": {
                medicineCache.remove(entity);
                break;
            }
            case "com.epam.jwd.domain.Order": {
                orderCache.remove(entity);
                break;
            }
            case "com.epam.jwd.domain.Recipe": {
                recipeCache.remove(entity);
                break;
            }
            case "com.epam.jwd.domain.User": {
                userCache.remove(entity);
                break;
            }
            case "com.epam.jwd.domain.BankAccount": {
                bankAccountCache.remove(entity);
                break;
            }
            case "com.epam.jwd.domain.Payment": {
                paymentCache.remove(entity);
                break;
            }
            case "com.epam.jwd.domain.RecipeProlongationRequest": {
                requestCache.remove(entity);
                break;
            }
            case "com.epam.jwd.domain.AppointmentWindow": {
                windowCache.remove(entity);
                break;
            }
            default: {
            }
        }
    }

    public void updateInCache(Entity oldEntity, Entity newEntity) {
        removeFromCache(oldEntity);
        addToCache(newEntity);
    }

    public void initCache() throws DAOException {

        userCache.addAll(UserDao.getInstance().getAll());

        medicineCache.addAll(MedicineDao.getInstance().getAll());

        appointmentCache.addAll(AppointmentDao.getInstance().getAll());


        orderCache.addAll(OrderDao.getInstance().getAll());


        recipeCache.addAll(RecipeDao.getInstance().getAll());


        bankAccountCache.addAll(BankAccountDao.getInstance().getAll());


        paymentCache.addAll(PaymentDao.getInstance().getAll());


        requestCache.addAll(RecipeProlongationRequestDao.getInstance().getAll());


        windowCache.addAll(AppointmentWindowDao.getInstance().getAll());

    }
}
