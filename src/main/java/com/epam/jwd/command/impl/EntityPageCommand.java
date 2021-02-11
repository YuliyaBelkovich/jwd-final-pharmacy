package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.PageName;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.entity.EntityService;

public class EntityPageCommand <T extends Entity> implements Command {
    private EntityService<T> service;
    private String entityName;
    private PageName entityPage;

    public EntityPageCommand(EntityService<T> service, String entityName, PageName entityPage) {
        this.service = service;
        this.entityName = entityName;
        this.entityPage = entityPage;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id = Integer.parseInt(requestContext.getParameter(entityName + "_id"));
        Entity entity =null;
        try {
            entity = service.findById(id);
        } catch (EntityNotFoundException | DAOException e) {
            requestContext.setAttribute("Name", entityName + "not found");
        }
        requestContext.setAttribute(entityName, entity);
        requestContext.getSession().setAttribute("previousPage",requestContext.getUrl());
        return entityPage::getJspFileName;
    }
}
