package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;

public class ShowHiddenScopeCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("showHiddenScope", true);
        return () -> requestContext.getParameter("url");
    }
}
