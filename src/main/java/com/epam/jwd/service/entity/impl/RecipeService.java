package com.epam.jwd.service.entity.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.RecipeCriteria;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.dao.impl.RecipeDao;
import com.epam.jwd.domain.Appointment;
import com.epam.jwd.domain.Recipe;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.factory.impl.RecipeFactory;
import com.epam.jwd.service.entity.EntityService;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
/**
 * Service class for {@link Recipe} class
 * Performs CRUD operations
 */
public class RecipeService  implements EntityService<Recipe> {

    private static RecipeService recipeService;

    private RecipeService(){}

    public static RecipeService getInstance() {
        if (recipeService == null) {
            return recipeService = new RecipeService();
        } else return recipeService;
    }

    @Override
    public EntityDao<Recipe> getEntityDao() {
        EntityDao<Recipe> dao =  RecipeDao.getInstance();
        return dao;
    }

    @Override
    public CopyOnWriteArrayList<Recipe> getEntityCache() {
        return getApplicationContext().getCache(Recipe.class);
    }

    @Override
    public EntityFactory<Recipe> getEntityFactory() {
        return RecipeFactory.getInstance();
    }

    @Override
    public List<Recipe> findInCashByCriteria(Criteria<Recipe> criteria) {
        RecipeCriteria recipeCriteria = (RecipeCriteria) criteria;
        return getEntityCache()
                .stream()
                .filter(recipe -> {
                    int id = recipeCriteria.getId();
                    return id == 0 || id == recipe.getId();
                })
                .filter(recipe -> {
                    int id = recipeCriteria.getMedicineId();
                    return id == 0 || id == recipe.getMedicineId();
                })
                .filter(recipe -> {
                    int id = recipeCriteria.getDoctorId();
                    return id == 0 || id == recipe.getDoctorId();
                })
                .filter(recipe -> {
                    int id = recipeCriteria.getPatientId();
                    return id == 0 || id == recipe.getPatientId();
                })
                .filter(recipe -> {
                    double dose = recipeCriteria.getDose();
                    return dose == 0 || dose == recipe.getDose();
                })
                .filter(recipe -> {
                    LocalDate date = recipeCriteria.getDate();
                    return date == null || date.equals(recipe.getDate());
                })
                .collect(Collectors.toList());
    }
}
