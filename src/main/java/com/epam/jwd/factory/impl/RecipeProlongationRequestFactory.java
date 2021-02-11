package com.epam.jwd.factory.impl;

import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.domain.RecipeRequestStatus;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.factory.EntityFactory;
import org.apache.log4j.Logger;

public class RecipeProlongationRequestFactory implements EntityFactory<RecipeProlongationRequest> {

    private static RecipeProlongationRequestFactory recipeProlongationRequestFactory = new RecipeProlongationRequestFactory();
    private static final Logger logger = Logger.getLogger(RecipeProlongationRequestFactory.class);

    private RecipeProlongationRequestFactory(){}

    public static RecipeProlongationRequestFactory getInstance() {
        return recipeProlongationRequestFactory;
    }

    @Override
    public RecipeProlongationRequest create(Object... args) throws FactoryException {

        RecipeProlongationRequest request;

        try{
            request = new RecipeProlongationRequest(
                    (int) args[0],
                    (int) args[1],
                    (int) args[2],
                    RecipeRequestStatus.resolveStatusByDBName((String) args[3])            );

        } catch (ClassCastException e){
            logger.error(e.getMessage());
            throw new FactoryException("Wrong arguments during the creation of the RecipeProlongationRequest object");
        }

        return request;
    }
}
