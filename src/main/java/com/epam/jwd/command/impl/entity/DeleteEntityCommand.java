package com.epam.jwd.command.impl.entity;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.PageName;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.Entity;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.service.EntityService;

public class DeleteEntityCommand<T extends Entity> implements Command {
    private String entityName;
    private EntityService<T> service;

    public DeleteEntityCommand(EntityService<T> service, String entityName) {
        this.service = service;
        this.entityName = entityName;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.setAttribute("Error", "");
        requestContext.setAttribute("Message", "");
        int id = 0;
        if (requestContext.hasParameter(entityName + "_id")) {
            id = Integer.parseInt(requestContext.getParameter(entityName + "_id"));
        }
        try {
            service.delete(service.findById(id));
        } catch (DAOException | EntityNotFoundException e) {
            requestContext.setAttribute("Error", e.getMessage());
            return()-> (String) requestContext.getSession().getAttribute("previousPage");
        }
        requestContext.setAttribute("Message", entityName + " successfully deleted!");
        return ()-> (String) requestContext.getSession().getAttribute("previousPage");
    }
}
