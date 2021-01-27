package com.epam.jwd.command.impl.auth;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.PageName;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;

public class LogOutCommand implements Command {
    private static final ResponseContext LOG_OUT = () -> "/pharmacy?command=start";

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("user_name", "");
        requestContext.getSession().setAttribute("user_id", "");
        requestContext.getSession().setAttribute("user_role", "GUEST");
        requestContext.getSession().setAttribute("user_status", "");
        requestContext.getSession().setAttribute("order", "");
        requestContext.getSession().setAttribute("basket", "");
        return LOG_OUT;
    }
}
