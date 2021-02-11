package com.epam.jwd.command.impl.entity.user;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.factory.impl.UserFactory;
import com.epam.jwd.service.entity.impl.UserService;
import com.epam.jwd.service.mail.MailService;

public class UpdateUserCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        int id;
        String name;
        String email;
        String status;
        String role;
        String url = (String) requestContext.getSession().getAttribute("previousPage");

        if (requestContext.hasParameter("user_id") && requestContext.hasParameter("user_name")
                && requestContext.hasParameter("user_email") && requestContext.hasParameter("user_status")
                && requestContext.hasParameter("user_role") && requestContext.hasParameter("user_password")) {
            id = Integer.parseInt(requestContext.getParameter("user_id"));
            name = requestContext.getParameter("user_name");
            email = requestContext.getParameter("user_email");
            status = requestContext.getParameter("user_status");
            role = requestContext.getParameter("user_role");
        } else {
            return () -> url + "&error=Missing+mandatory+field";
        }

        User user;
        try {
            User oldUser = UserService.getInstance().findById(id);
            user = UserFactory.getInstance().create(id, name, email, oldUser.getPassword(), role, status);
            UserService.getInstance().update(user);
        } catch (FactoryException | DAOException | EntityNotFoundException | ValidationException e) {
            return () -> url + "&error=" + e.getMessage().replace(" ", "+");
        }
        MailService.getInstance().sendTextEmail(email, "User Profile Update", "Your profile have been changed. Please check profile info to see current settings");
        return () -> url;
    }
}
