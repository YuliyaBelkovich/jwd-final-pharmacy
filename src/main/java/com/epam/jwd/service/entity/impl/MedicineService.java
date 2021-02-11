package com.epam.jwd.service.entity.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.MedicineCriteria;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.dao.impl.MedicineDao;
import com.epam.jwd.domain.Medicine;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.factory.impl.MedicineFactory;
import com.epam.jwd.service.entity.EntityService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class MedicineService implements EntityService<Medicine> {

    private static MedicineService medicineService;

    private MedicineService(){}

    public static MedicineService getInstance() {
        if (medicineService == null) {
            return medicineService = new MedicineService();
        } else return medicineService;
    }

    @Override
    public EntityDao<Medicine> getEntityDao() {
        EntityDao<Medicine> dao = MedicineDao.getInstance();
        return dao;
    }

    @Override
    public CopyOnWriteArrayList<Medicine> getEntityCache() {
        return getApplicationContext().getCache(Medicine.class);
    }

    @Override
    public EntityFactory<Medicine> getEntityFactory() {
        return MedicineFactory.getInstance();
    }

    @Override
    public List<Medicine> findInCashByCriteria(Criteria<Medicine> criteria) {
        MedicineCriteria medicineCriteria = (MedicineCriteria) criteria;
        return getEntityCache()
                .stream()
                .filter(medicine -> {
                    int id = medicineCriteria.getId();
                    return id == 0 || id == medicine.getId();
                })
                .filter(medicine -> {
                    String name = medicineCriteria.getName();
                    return name == null || name.equals(medicine.getName());
                })
                .filter(medicine -> {
                    double dose = medicineCriteria.getDose();
                    return dose == 0 || dose == medicine.getDose();
                })
                .filter(medicine -> {
                    boolean recipe = medicineCriteria.isRecipeRequirement();
                    boolean noRecipe = medicineCriteria.isNonRecipeRequirement();

                    if (!recipe || !noRecipe) {
                        if (recipe) {
                            return medicine.isRecipeRequirement();
                        }
                        if (noRecipe) {
                            return !medicine.isRecipeRequirement();
                        }
                        return true;
                    } else {
                        return true;
                    }
                })
                .filter(medicine -> {
                    double price = medicineCriteria.getPrice();
                    return price == 0 || price == medicine.getPrice();
                })
                .collect(Collectors.toList());
    }
}
