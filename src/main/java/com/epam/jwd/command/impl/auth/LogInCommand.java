package com.epam.jwd.command.impl.auth;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;
import com.epam.jwd.domain.User;
import com.epam.jwd.exception.AuthenticationException;
import com.epam.jwd.service.auth.LogInService;

/**
 * Command responsible for log in operation
 * Calls {@link com.epam.jwd.service.entity.impl.UserService} class for checking the presence of the given e-mail and password
 * Returns error message if some of the required fields are missing
 * Sets session attributes with {@link User} information
 */
public class LogInCommand implements Command {

    private static final ResponseContext LOG_IN = () -> "/pharmacy?command=go_to_main_page";


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        String email;
        String password;
        if (requestContext.hasParameter("email") && requestContext.hasParameter("password")) {
            email = requestContext.getParameter("email");
            password = requestContext.getParameter("password");
        } else {
            return () -> "/pharmacy?command=go_to_login_page&error=Missing+mandatory+field";
        }
        User user;
        try {
            user = LogInService.logIn(email, password);
        } catch (AuthenticationException e) {
            return ()->"/pharmacy?command=go_to_login_page&error="+e.getMessage().replace(" ","+");
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
