package com.epam.jwd.service.entity.impl;

import com.epam.jwd.criteria.Criteria;
import com.epam.jwd.criteria.RecipeProlongationRequestCriteria;
import com.epam.jwd.dao.EntityDao;
import com.epam.jwd.dao.impl.RecipeProlongationRequestDao;
import com.epam.jwd.domain.RecipeProlongationRequest;
import com.epam.jwd.domain.RecipeRequestStatus;
import com.epam.jwd.factory.EntityFactory;
import com.epam.jwd.factory.impl.RecipeProlongationRequestFactory;
import com.epam.jwd.service.entity.EntityService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class RecipeProlongationRequestService  implements EntityService<RecipeProlongationRequest> {
    private static RecipeProlongationRequestService instance = new RecipeProlongationRequestService();

    private RecipeProlongationRequestService(){
    }

    public static RecipeProlongationRequestService getInstance() {
        return instance;
    }
    @Override
    public EntityDao<RecipeProlongationRequest> getEntityDao() {
        EntityDao<RecipeProlongationRequest> dao =  RecipeProlongationRequestDao.getInstance();
        return dao;
    }

    @Override
    public CopyOnWriteArrayList<RecipeProlongationRequest> getEntityCache() {
        return getApplicationContext().getCache(RecipeProlongationRequest.class);
    }

    @Override
    public EntityFactory<RecipeProlongationRequest> getEntityFactory() {
        return RecipeProlongationRequestFactory.getInstance();
    }

    @Override
    public List<RecipeProlongationRequest> findInCashByCriteria(Criteria<RecipeProlongationRequest> criteria) {
        RecipeProlongationRequestCriteria requestCriteria = (RecipeProlongationRequestCriteria) criteria;
        return getEntityCache()
                .stream()
                .filter(request -> {
                    int id = requestCriteria.getId();
                    return id == 0 || id == request.getId();
                })
                .filter(request -> {
                    int id = requestCriteria.getRecipeId();
                    return id == 0 || id == request.getRecipeId();
                })
                .filter(request -> {
                    int id = requestCriteria.getDoctorId();
                    return id == 0 || id == request.getDoctorId();
                })
                .filter(request -> {
                    RecipeRequestStatus status = requestCriteria.getStatus();
                    return status == null || request.getStatus().equals(status);
                })
                .collect(Collectors.toList());
    }
}
