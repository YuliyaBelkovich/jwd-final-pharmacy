package com.epam.jwd.command.impl.auth;

import com.epam.jwd.command.Basket;
import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.AuthenticationException;
import com.epam.jwd.service.LogInService;

public class LogInCommand implements Command {

    private static final ResponseContext LOGIN_ERROR_FIELD = () -> "/pharmacy?command=go_to_login_page";
    private static final ResponseContext LOG_IN = () -> "/pharmacy?command=go_to_main_page";


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        String email;
        String password;
        if (requestContext.hasParameter("email") && requestContext.hasParameter("password")) {
            email = requestContext.getParameter("email");
            password = requestContext.getParameter("password");
        } else {
            requestContext.getSession().setAttribute("Error", "Missing mandatory field");
            return LOGIN_ERROR_FIELD;
        }
        User user = null;
        try {
            user = LogInService.logIn(email, password);
        } catch (AuthenticationException e) {
            requestContext.getSession().setAttribute("Error", e.getMessage());
            return LOGIN_ERROR_FIELD;
        }
        requestContext.getSession().setAttribute("user_name", user.getName());
        requestContext.getSession().setAttribute("user_id", user.getId());
        requestContext.getSession().setAttribute("user_email", user.getEmail());
        requestContext.getSession().setAttribute("user_role", user.getRole().getBaseName());
        requestContext.getSession().setAttribute("user_status", user.getStatus().getDbName());
        requestContext.getSession().setAttribute("Error", "");
        return LOG_IN;
    }
}
