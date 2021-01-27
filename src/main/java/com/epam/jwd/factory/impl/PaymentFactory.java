package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.Payment;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.EntityFactory;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class PaymentFactory implements EntityFactory<Payment> {

    private static PaymentFactory paymentFactory = new PaymentFactory();
    private static final Logger logger = Logger.getLogger(PaymentFactory.class);

    private PaymentFactory(){}

    public static PaymentFactory getInstance() {
        return paymentFactory;
    }

    @Override
    public Payment create(Object... args) throws FactoryException {
        Payment payment;

        try{
            payment = new Payment((int) args[0],
                    (double) args[1],
                    (String) args[2] ,
                    (LocalDateTime) args[3]);
        } catch (ClassCastException e){
            logger.error("Class cast exception");
            throw new FactoryException("Wrong arguments while creating the PAYMENT object");
        }
        return payment;
    }
}
