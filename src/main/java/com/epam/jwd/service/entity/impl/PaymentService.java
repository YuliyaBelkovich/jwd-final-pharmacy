package com.epam.jwd.service.entity.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.PaymentCriteria;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.dao.impl.PaymentDao;
import com.epam.jwd.domain.Payment;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.factory.impl.PaymentFactory;
import com.epam.jwd.service.entity.EntityService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class PaymentService implements EntityService<Payment> {

    private static PaymentService service = new PaymentService();

    private PaymentService(){}

    public static PaymentService getInstance() {
        return service;
    }
    @Override
    public EntityDao<Payment> getEntityDao() {
        EntityDao<Payment> dao =  PaymentDao.getInstance();
        return dao;
    }

    @Override
    public CopyOnWriteArrayList<Payment> getEntityCache() {
        return getApplicationContext().getCache(Payment.class);
    }

    @Override
    public EntityFactory<Payment> getEntityFactory() {
        return PaymentFactory.getInstance();
    }

    @Override
    public List<Payment> findInCashByCriteria(Criteria<Payment> criteria) {
        PaymentCriteria paymentCriteria = (PaymentCriteria) criteria;
        return getEntityCache()
                .stream()
                .filter(payment -> {
                    int id = paymentCriteria.getId();
                    return id == 0 || id == payment.getId();
                })
                .filter(payment -> {
                    double sum = paymentCriteria.getSum();
                    return sum == 0 || sum == payment.getSum();
                })
                .filter(payment -> {
                    String IBAN = paymentCriteria.getIBAN();
                    return IBAN == null || payment.getIBAN().equals(IBAN);
                })
                .filter(payment -> {
                    LocalDateTime dateTime = paymentCriteria.getDateTime();
                    return dateTime == null || payment.getDateTime().equals(dateTime);
                })
                .collect(Collectors.toList());
    }
}
