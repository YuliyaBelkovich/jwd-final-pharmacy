package com.epam.jwd.factory.impl;

import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.domain.Medicine;
import com.epam.jwd.exception.FactoryException;

public class MedicineFactory implements EntityFactory<Medicine> {

    private static MedicineFactory medicineFactory = new MedicineFactory();

    private MedicineFactory() {
    }

    public static MedicineFactory getInstance() {
        return medicineFactory;
    }

    @Override
    public Medicine create(Object... args) throws FactoryException {
        Medicine medicine;
        try {
            medicine = new Medicine((int) args[0],
                    (String) args[1],
                    (double) args[2],
                    (boolean) args[3],
                    (String) args[4],
                    (double) args[5]);
        } catch (ClassCastException e) {
            throw new FactoryException("Wrong arguments during the creation of the Medicine object");
        }
        return medicine;
    }
}
