package com.epam.jwd.command.impl.auth;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.RegisterService;
import com.epam.jwd.service.mail.MailService;

public class RegisterCommand implements Command {

    private static final ResponseContext REGISTER_ERROR_FIELD = () -> "/pharmacy?command=go_to_register_page";
    private static final ResponseContext REGISTER_SUCCESS = () -> "/pharmacy?command=go_to_login_page";

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        String email;
        String password;
        String confirmedPassword;
        String name;
        String role;

        if (requestContext.hasParameter("email") && requestContext.hasParameter("password")
                && requestContext.hasParameter("confirmed_password") && requestContext.hasParameter("user_name")
                && requestContext.hasParameter("user_role")) {
            email = requestContext.getParameter("email");
            password = requestContext.getParameter("password");
            confirmedPassword = requestContext.getParameter("confirmed_password");
            name = requestContext.getParameter("user_name");
            role = requestContext.getParameter("user_role");
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return REGISTER_ERROR_FIELD;
        }

        if (password.equals(confirmedPassword)) {
            try {
                if (role.equals("PATIENT")) {
                    RegisterService.register(email, password, name, role, "ACTIVE");
                }
                if (role.equals("DOCTOR")) {
                    MailService.getInstance().sendTextEmail(email,"Doctor Status","Congratulations, you've just registered as a doctor on SACRED HEART PHARMACY site. Please, send to this email address scan of your medical diploma and other accompanying documents. As soon as our specialists confirm your profile, you will be notified by email.");
                    RegisterService.register(email, password, name, role, "PENDING");
                }
            } catch (DAOException | FactoryException | EntityNotFoundException | ValidationException e) {
                requestContext.getSession().setAttribute("Error", e.getMessage());
            }
        } else {
            requestContext.getSession().setAttribute("Error", "Passwords doesn't match");
            return REGISTER_ERROR_FIELD;
        }

        requestContext.getSession().setAttribute("Error", "");
        MailService.getInstance().sendTextEmail(email,"Registration confirmation","You've just registered on SACRED HEART PHARMACY site! Congrats!");
        return REGISTER_SUCCESS;
    }
}
