package com.epam.jwd.factory;

import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.FactoryException;

public interface EntityFactory <T extends Entity>{

    T create(Object... args) throws FactoryException;

}
