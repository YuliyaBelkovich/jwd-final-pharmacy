package com.epam.jwd.command.impl.auth;

import com.epam.jwd.command.Command;
import com.epam.jwd.context.RequestContext;
import com.epam.jwd.context.ResponseContext;

/**
 * Command responsible for logging out
 * Sets all session attributes describing user to default
 */
public class LogOutCommand implements Command {
    private static final ResponseContext LOG_OUT = () -> "/pharmacy?command=start";

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("user_name", null);
        requestContext.getSession().setAttribute("user_id", null);
        requestContext.getSession().setAttribute("user_role", "GUEST");
        requestContext.getSession().setAttribute("user_status", null);
        requestContext.getSession().setAttribute("order", null);
        requestContext.getSession().setAttribute("basket", null);
        return LOG_OUT;
    }
}
