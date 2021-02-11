package com.epam.jwd.command.impl.auth;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.exception.DAOException;
import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.exception.FactoryException;
import com.epam.jwd.exception.ValidationException;
import com.epam.jwd.service.auth.RegisterService;
import com.epam.jwd.service.mail.MailService;

public class RegisterCommand implements Command {

    private static final ResponseContext REGISTER_SUCCESS = () -> "/pharmacy?command=go_to_login_page&message=Registration+was+successfull.+Please,+log+in";

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
            return () -> "/pharmacy?command=go_to_register_page&error=Missing+mandatory+field";
        }
        System.out.println(name);
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
                return () -> "/pharmacy?command=go_to_register_page&error=User+already+exists";
            }
        } else {
            return () -> "/pharmacy?command=go_to_register_page&error=Passwords+doesn't+match";
        }

        MailService.getInstance().sendTextEmail(email,"Registration confirmation","You've just registered on SACRED HEART PHARMACY site! Congrats!");
        return REGISTER_SUCCESS;
    }
}
