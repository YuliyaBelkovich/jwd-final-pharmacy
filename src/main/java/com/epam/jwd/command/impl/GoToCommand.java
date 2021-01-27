package com.epam.jwd.command.impl;

import com.epam.jwd.command.Command;
import com.epam.jwd.command.PageName;
import com.epam.jwd.command.RequestContext;
import com.epam.jwd.command.ResponseContext;

public class GoToCommand implements Command {

    private ResponseContext PAGE;

    public GoToCommand(PageName name) {
        PAGE = name::getJspFileName;
    }

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        requestContext.getSession().setAttribute("previousPage",requestContext.getUrl());
        return PAGE;
    }
}
