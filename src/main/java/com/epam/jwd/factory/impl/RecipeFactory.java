package com.epam.jwd.factory.impl;

import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.domain.Recipe;
import com.epam.jwd.exception.FactoryException;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Date;


public class RecipeFactory implements EntityFactory<Recipe> {

    private static RecipeFactory recipeFactory = new RecipeFactory();
    private static final Logger logger = Logger.getLogger(RecipeFactory.class);

    private RecipeFactory(){}

    public static RecipeFactory getInstance() {
        return recipeFactory;
    }

    @Override
    public Recipe create(Object... args) throws FactoryException {
        Recipe recipe;

        try {
            recipe = new Recipe((int) args[0],
                    (int) args[1],
                    (int) args[2],
                    (double) args[3],
                    (int) args[4],
                    (int) args[5],
                    (LocalDate) args[6]);
        } catch (ClassCastException e){
            logger.error("Class cast exception");
            throw new FactoryException("Wrong arguments while creating the Recipe object");
        }
        return recipe;
    }
}
