package com.epam.jwd.command.impl.entity.user;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.UserFactory;
import com.epam.jwd.service.impl.UserService;
import com.epam.jwd.service.mail.MailService;

public class UpdateUserCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("Message", "");
        requestContext.getSession().setAttribute("Error", "");

        int id;
        String name;
        String email;
        String status;
        String role;
        String password;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("user_id") && requestContext.hasParameter("user_name")
                && requestContext.hasParameter("user_email") && requestContext.hasParameter("user_status")
                && requestContext.hasParameter("user_role") && requestContext.hasParameter("user_password")) {
            id = Integer.parseInt(requestContext.getParameter("user_id"));
            name = requestContext.getParameter("user_name");
            email = requestContext.getParameter("user_email");
            status = requestContext.getParameter("user_status");
            role = requestContext.getParameter("user_role");
            password = requestContext.getParameter("user_password");
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return () -> url;
        }

        User user;
        try {
            user = UserFactory.getInstance().create(id, name, email, password, role, status);
            UserService.getInstance().update(user);
        } catch (FactoryException | DAOException | EntityNotFoundException | ValidationException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            return () -> url;
        }
        requestContext.getSession().setAttribute("Error", "");
        requestContext.getSession().setAttribute("Message", "User updated!\n");
        MailService.getInstance().sendTextEmail(email,"User Profile Update","Your profile have been changed. Please check profile info to see current settings");
        return () -> url;
    }
}
